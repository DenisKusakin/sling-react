package com.cathcart93.sling.components

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import com.google.gson.annotations.SerializedName

@ReactController(componentName = "Container")
class Container(val components: List<Any> = emptyList(),
                availableComponents: List<ComponentElement> = defaultComponents(),
                path: String? = null, componentsMeta: List<ComponentMeta> = emptyList()) : IReactController {

    @SerializedName("__type")
    val type = "Container"

    @SerializedName("__dialog")
    var dialog: Dialog? = null

    init {
        if (path != null) {
            dialog = Dialog(if (path.endsWith("/")) path else "$path/", availableComponents, componentsMeta)
        }
    }

    data class Dialog(val path: String, val components: List<ComponentElement>, val meta: List<ComponentMeta>)

    class ComponentElement(
            val name: String,
            val description: String = "",
            props: Map<String, String> = emptyMap(),
            resourceType: String = "react/components"
    ) {
        val __props: MutableMap<String, String> = HashMap()

        init {
            this.__props.put("sling:resourceType", resourceType)
            this.__props.put(":nameHint", name)
            this.__props.put(":order", "last")
            this.__props.put("title", "Edit Title Here")
            this.__props.put("component", name)
            this.__props.putAll(props)
        }
    }

    data class ComponentMeta(val path: String)
}

fun defaultComponents(): List<Container.ComponentElement> {
    val title = Container.ComponentElement("Title", "Can be used as main page title")
    return listOf<Container.ComponentElement>(title)
}