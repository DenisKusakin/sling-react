package com.cathcart93.sling.components.models.spectacle.dialogs

import com.cathcart93.sling.components.models.spectacle.api.SimpleDialog
import com.cathcart93.sling.components.models.spectacle.api.SimpleDialog.SelectProperty.SelectPropertyOption
import org.apache.sling.api.resource.Resource

fun simpleDialog(resource: Resource, block: DialogContext.() -> Unit): SimpleDialog {
    val dialogContext = DialogContext(resource)
    block(dialogContext)
    return dialogContext
}

//TODO: How to not use transient?
class DialogContext(@Transient private val resource: Resource) : SimpleDialog {
    override val props: ArrayList<SimpleDialog.Property> = ArrayList()
    override val path: String = resource.path

    fun text(name: String, title: String) {
        this.props.add(SimpleDialog.TextProperty(title = title, name = name,
                value = resource.valueMap.get(name, String::class.java)))
    }

    fun multilineText(name: String, title: String) {
        this.props.add(SimpleDialog.MultilineTextProperty(title = title, name = name,
                value = resource.valueMap.get(name, String::class.java)))
    }

    fun checkbox(name: String, title: String) {
        this.props.add(SimpleDialog.CheckboxProperty(title = title, name = name,
                value = resource.valueMap.get(name, false)))
    }

    fun select(name: String, title: String, block: SelectContext.() -> Unit) {
        val selectContext = SelectContext()
        block(selectContext)
        this.props.add(SimpleDialog.SelectProperty(title = title, name = name,
                value = resource.valueMap.get(name, String::class.java),
                options = selectContext.options))
    }

    fun color(name: String, title: String) {
        this.props.add(SimpleDialog.ColorProperty(title = title, name = name,
                value = resource.valueMap.get(name, String::class.java)))
    }

    class SelectContext {
        val options = ArrayList<SelectPropertyOption>()

        fun option(label: String, value: String) {
            options.add(SelectPropertyOption(value = value, label = label))
        }
    }
}