package com.cathcart93.sling.components.models.spectacle

import com.cathcart93.sling.components.models.spectacle.api.SimpleDialog
import org.apache.sling.api.resource.Resource

fun containerDialog(resource: Resource, block: ContainerContext.() -> Unit): ContainerDialog {
    val containerContext = ContainerContext()
    val meta = resource.children.map { MetaItem(it.path) }
    block(containerContext)
    return ContainerDialog("${resource.path}/", meta, containerContext.components)
}

class ContainerDialog(val path: String, val meta: List<MetaItem>, val components: List<Component>)

class MetaItem(val path: String)

class Component(val name: String, val description: String = name, val __props: Map<String, Any> = HashMap())

class ContainerContext {
    val components = ArrayList<Component>()

    fun component(name: String, description: String, block: ComponentContext.() -> Unit) {
        val componentContext = ComponentContext(name)
        block(componentContext)
        components.add(Component(name = name, description = description, __props = componentContext.props))
    }

    fun component(name: String) {
        components.add(Component(name = name, description = "", __props = HashMap()))
    }
}

class ComponentContext(componentName: String) {
    val props = HashMap<String, String>()

    init {
        props.put(":order", "last")
        props.put(":nameHint", componentName)
        props.put("component", componentName)
    }

    fun nodeNameHint(nodeNameHint: String) {
        props.put(":nameHint", nodeNameHint)
    }

    fun prop(name: String, value: String) {
        props.put(name, value)
    }
}

class ThemedContainer(containerDialog: ContainerDialog, val theme: SimpleDialog) {
    val components: List<Component> = containerDialog.components
    val meta: List<MetaItem> = containerDialog.meta
    val path: String = containerDialog.path
}