package com.cathcart93.sling.components.models.spectacle.impl.builder

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.*

open class SimpleDialogBuilder {
    val props: MutableList<ReactProp> = mutableListOf()
}

class EditDialog : SpectacleTag, SimpleDialogBuilder() {
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
    //val props: MutableList<ReactProp> = mutableListOf()
}

data class MoveInfoItem(val name: String, val url: String)

class Container(
        val children: List<SpectacleTag>,
        val resourcePath: String,
        val components: List<ContainerComponent> = mutableListOf(),
        val moveInfo: List<MoveInfoItem> = emptyList()) : SpectacleTag {
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
                        ),
                        "moveInfo" to ArrayProp(
                                moveInfo.map {
                                    ObjectProps(mapOf(
                                            "url" to it.url.toReactProp(),
                                            "nodeName" to it.name.toReactProp()
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

fun SimpleDialogBuilder.text(name: String, title: String, value: String) {
    this.props.add(ObjectProps(mapOf(
            "name" to name.toReactProp(),
            "title" to title.toReactProp(),
            "value" to value.toReactProp(),
            "type" to "text".toReactProp()
    )))
}

fun SimpleDialogBuilder.multilineText(name: String, title: String, value: String) {
    this.props.add(ObjectProps(mapOf(
            "name" to name.toReactProp(),
            "title" to title.toReactProp(),
            "value" to value.toReactProp(),
            "type" to "textarea".toReactProp()
    )))
}

fun SimpleDialogBuilder.checkbox(name: String, title: String, value: Boolean) {
    this.props.add(ObjectProps(mapOf(
            "name" to name.toReactProp(),
            "title" to title.toReactProp(),
            "value" to value.toReactProp(),
            "type" to "checkbox".toReactProp()
    )))
}

fun SimpleDialogBuilder.color(name: String, title: String, value: String?) {
    this.props.add(ObjectProps(mapOf(
            "type" to "color".toReactProp(),
            "name" to name.toReactProp(),
            "title" to title.toReactProp(),
            "value" to value?.toReactProp()
    ).filter { it.value != null }.map { it.key to it.value!! }.toMap()))
}

fun SimpleDialogBuilder.select(name: String, title: String, value: String, options: List<SelectOption>) {
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

class SystemButtonsContainer(private vararg val buttons: SpectacleTag) : SpectacleTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(name = "SystemButtonsContainer", children = buttons.map { it.toReactElement() })
    }
}

class SlidePropertiesButton(val title: String, val editUrl: String, val props: List<ReactProp>) : SpectacleTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(name = "SlidePropertiesButton", props = mapOf(
                "editUrl" to editUrl.toReactProp(),
                "editDialog" to ArrayProp(this.props),
                "title" to title.toReactProp()
        ))
    }
}

class AddSlideButton(val resourcePath: String) : SpectacleTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(name = "AddSlideButton", props = mapOf(
                "url" to resourcePath.toReactProp(),
                "props" to ObjectProps(mapOf(
                        ":nameHint" to "slide".toReactProp()
                ))
        ))
    }
}

class DeleteSlideButton(val resourcePath: String) : SpectacleTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(name = "DeleteSlideButton", props = mapOf(
                "url" to resourcePath.toReactProp()
        ))
    }
}

fun propertiesButton(title: String, editUrl: String, block: SimpleDialogBuilder.() -> Unit): SlidePropertiesButton {
    val builder = SimpleDialogBuilder()
    block(builder)
    return SlidePropertiesButton(title, editUrl, builder.props)
}