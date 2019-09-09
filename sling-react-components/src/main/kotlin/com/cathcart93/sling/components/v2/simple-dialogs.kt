package com.cathcart93.sling.components.v2

import org.apache.sling.api.resource.Resource

class Dialog {
    val items = mutableListOf<PrimitiveProp>()

    fun text(name: String, propsBuilder: TextWidgetProps.() -> Unit) {
        val textProps = TextWidgetProps(name)
        propsBuilder(textProps)
        items += ObjectProp(mapOf(
                "name" to StringProp(name),
                "type" to StringProp("text"),
                "title" to StringProp(textProps.title),
                "value" to StringProp(textProps.value ?: "")
        ))
    }

    fun multilineText(name: String, propsBuilder: TextWidgetProps.() -> Unit) {
        val textProps = TextWidgetProps(name)
        propsBuilder(textProps)
        items += ObjectProp(mapOf(
                "name" to StringProp(name),
                "type" to StringProp("textarea"),
                "title" to StringProp(textProps.title),
                "value" to StringProp(textProps.value ?: "")
        ))
    }

    fun checkbox(name: String, propsBuilder: CheckboxProps.() -> Unit) {
        val props = CheckboxProps(name)
        propsBuilder(props)
        items += ObjectProp(mapOf(
                "name" to StringProp(name),
                "type" to StringProp("checkbox"),
                "title" to StringProp(props.title),
                "value" to BooleanProp(props.value)
        ))
    }

    fun color(name: String, propsBuilder: ColorProps.() -> Unit) {
        val props = ColorProps(name)
        propsBuilder(props)
        items += ObjectProp(mapOf(
                "name" to StringProp(name),
                "type" to StringProp("color"),
                "title" to StringProp(props.title),
                "value" to StringProp(props.value ?: "")
        ))
    }

    fun select(name: String, propsBuilder: SelectOptions.() -> Unit) {
        val props = SelectOptions(name)
        propsBuilder(props)
        items += ObjectProp(mapOf(
                "name" to StringProp(name),
                "type" to StringProp("select"),
                "title" to StringProp(props.title),
                "value" to StringProp(props.value ?: ""),
                "options" to ArrayProp(
                        props.options.map {
                            ObjectProp(mapOf(
                                    "label" to StringProp(it.label),
                                    "value" to StringProp(it.value)
                            ))
                        }
                )
        ))
    }
}

class TextWidgetProps(val name: String) {
    var title: String = ""
    var value: String? = null
}

class CheckboxProps(val name: String) {
    var title: String = ""
    var value: Boolean = false
}

class ColorProps(val name: String) {
    var title: String = ""
    var value: String? = null
}

data class SelectOptions(val name: String) {
    var title: String = ""
    var value: String? = null
    var options: List<Option> = emptyList()

    data class Option(val value: String, val label: String)
}


fun Component<Resource>.editableComponent(mapResourceToDialog: Dialog.(resource: Resource) -> Unit): Component<Resource> {
    return object : Component<Resource> {
        override fun render(props: Resource): Element {
            val dialog = Dialog()
            mapResourceToDialog(dialog, props)

            return "EditDialog" {
                "editUrl" to props.path
                "deleteUrl" to props.path
                "editDialog" to ArrayProp(dialog.items)
                "children" to this@editableComponent {
                    props
                }
            }
        }
    }
}

object Container : Component<List<Element>> {
    override fun render(props: List<Element>): Element {
        return "Container" {
            "components" to ArrayProp(emptyList())
            "moveInfo" to {

            }
        }
    }

}

