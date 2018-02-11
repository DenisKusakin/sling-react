package com.cathcart93.sling.components.models.spectacle

import com.cathcart93.sling.components.Container
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue
import javax.annotation.PostConstruct

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@ReactController("Slide")
class SlideModel : IReactController, Slide {

    @SlingObject
    private lateinit var resource: Resource

    @ReactProp
    private lateinit var children: List<SlideComponent>

    @ReactProp("__dialog")
    private lateinit var dialog: Any

    @ValueMapValue
    @ReactProp
    private var bgColor: String? = null

    @ValueMapValue
    @ReactProp
    private var textColor: String? = null

    @PostConstruct
    fun init() {
        children = resource.children
                .mapNotNull { it.adaptTo(IReactController::class.java) }
                .filter { it is SlideComponent }
                .map { it as SlideComponent }

        val containerDialog = containerDialog(resource) {
            component("Heading", "Text Component") {
                prop("text", "Edit Title Here")
                prop("size", "3")
                prop("size", "3")
                prop("fit", "false")
            }
            component("Text", "Just a text component") {
                prop("text", "Edit Text Component Here")
            }
            component("BlockQuote", "Quote Block component") {
                prop("quote", "Edit Quote")
                prop("cite", "Author")
            }
            component("CodePane", "Use to show highlighted code"){
                prop("source", "function test(){}")
                prop("lang", "javascript")
            }
        }

        val theme = dialog(resource) {
            select("bgColor", "Background Color") {
                option("Primary Color", "primary")
                option("Secondary Color", "secondary")
            }
            select("textColor", "Text Color") {
                option("Primary Color", "primary")
                option("Secondary Color", "secondary")
            }
        }

        dialog = ThemedContainer(containerDialog, theme)
    }
}