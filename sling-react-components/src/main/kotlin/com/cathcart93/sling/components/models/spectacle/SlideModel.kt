package com.cathcart93.sling.components.models.spectacle

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import javax.annotation.PostConstruct

@Model(adaptables = [Resource::class])
@ReactController("Slide")
class SlideModel : IReactController, Slide {

    @SlingObject
    private lateinit var resource: Resource

    @ReactProp
    private lateinit var children: List<SlideComponent>

    @ReactProp("__dialog")
    private lateinit var dialog: Any

    @PostConstruct
    fun init() {
        children = resource.children
                .mapNotNull { it.adaptTo(IReactController::class.java) }
                .filter { it is SlideComponent }
                .map { it as SlideComponent }
        dialog = Dialog(
                resource.children
                        .filter { it.adaptTo(IReactController::class.java) != null }
                        .filter { it.adaptTo(IReactController::class.java) is SlideComponent },
                "${resource.path}/",
                defaultComponents()
        )
    }

    class Dialog(children: List<Resource>, val path: String, val components: List<Component> = emptyList()) {
        private val meta: List<MetaItem> = children.map {
            MetaItem(it.path)
        }

        class MetaItem(private val path: String)
        class Component(val name: String, val description: String = name, val __props: Map<String, Any> = HashMap())

    }

    fun defaultComponents(): List<Dialog.Component> {
        val list = ArrayList<Dialog.Component>()
        val props = HashMap<String, String>()
        props.put(":nameHint", "heading")
        props.put(":order", "last")
        props.put("text", "Edit Title Here")
        props.put("size", "3")
        props.put("fit", "false")
        props.put("component", "Heading")
        val heading = Dialog.Component("Heading", "Can be used as Slide Heading", props)
        list.add(heading)

        val textProps = HashMap<String, String>()
        textProps.put(":nameHint", "heading")
        textProps.put(":order", "last")
        textProps.put("text", "Edit Text Component Here")
        textProps.put("component", "Text")
        val textComponent = Dialog.Component("Text", "Just a text component", textProps)
        list.add(textComponent)

        return list
    }
}