package com.cathcart93.sling.components.models.spectacle.impl.builder

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.*

class EditDialog : SpectacleTag {
    override fun toReactElement(): ReactElement {
        if (component == null) {
            return ReactElement("Error")
        }
        val componentReactElement = component!!.toReactElement()
        val props = mutableMapOf<String, ReactProp?>(
                "editUrl" to editUrl?.toReactProp(),
                "deleteUrl" to deleteUrl?.toReactProp(),
                "editDialog" to ArrayProp(this.props)
        )
        return ReactElement(
                name = "EditDialog",
                props = props.filter { it.value != null }.map { it.key to it.value!! }.toMap(),
                children = listOf(componentReactElement)
        )
    }

    var component: SpectacleTag? = null
    var editUrl: String? = null
    var deleteUrl: String? = null
    val props: MutableList<ReactProp> = mutableListOf()
}

class Container(val children: List<SpectacleTag>, val resourcePath: String, val components: List<ContainerComponent> = mutableListOf()) : SpectacleTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(
                name = "Container",
                children = children.map { it.toReactElement() },
                props = mapOf(
                        "resourcePath" to resourcePath.toReactProp(),
                        "components" to ArrayProp(
                                components.map {
                                    ObjectProps(mapOf(
                                            "props" to ObjectProps(mapOf(
                                                    ":nameHint" to it.nameHint.toReactProp(),
                                                    ":order" to "last".toReactProp(),
                                                    "component" to it.name.toReactProp()
                                            )),
                                            "title" to it.title.toReactProp(),
                                            "description" to it.description?.toReactProp()
                                    ))
                                }
                        )
                )
        )
    }
}

class ContainerComponent(
        val name: String,
        val nameHint: String = name,
        val title: String = name,
        val description: String? = null)

fun SpectacleTag.edit(block: EditDialog.() -> Unit): EditDialog {
    val edit = EditDialog()
    edit.component = this
    block(edit)
    return edit
}

fun EditDialog.text(name: String, title: String, value: String) {
    this.props.add(ObjectProps(mapOf(
            "name" to name.toReactProp(),
            "title" to title.toReactProp(),
            "value" to value.toReactProp(),
            "type" to "text".toReactProp()
    )))
}

fun EditDialog.multilineText(name: String, title: String, value: String) {
    this.props.add(ObjectProps(mapOf(
            "name" to name.toReactProp(),
            "title" to title.toReactProp(),
            "value" to value.toReactProp(),
            "type" to "textarea".toReactProp()
    )))
}

fun EditDialog.checkbox(name: String, title: String, value: Boolean) {
    this.props.add(ObjectProps(mapOf(
            "name" to name.toReactProp(),
            "title" to title.toReactProp(),
            "value" to value.toReactProp(),
            "type" to "checkbox".toReactProp()
    )))
}

fun EditDialog.color(name: String, title: String, value: String?) {
    this.props.add(ObjectProps(mapOf(
            "type" to "color".toReactProp(),
            "name" to name.toReactProp(),
            "title" to title.toReactProp(),
            "value" to value?.toReactProp()
    ).filter { it.value != null }.map { it.key to it.value!! }.toMap()))
}

fun EditDialog.select(name: String, title: String, value: String, options: List<SelectOption>) {
    this.props.add(ObjectProps(mapOf(
            "type" to "select".toReactProp(),
            "options" to ArrayProp(options.map {
                ObjectProps(mapOf(
                        "label" to it.label.toReactProp(),
                        "value" to it.value.toReactProp()
                ))
            }),
            "value" to value.toReactProp(),
            "name" to name.toReactProp(),
            "title" to title.toReactProp()
    )))
}

data class SelectOption(val label: String, val value: String)

object EditModeToggler : SpectacleTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(name = "EditModeToggler")
    }
}