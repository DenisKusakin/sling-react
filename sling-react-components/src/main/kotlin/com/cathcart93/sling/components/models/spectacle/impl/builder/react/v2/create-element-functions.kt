package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

fun <T> createElement(component: Component<T>, props: T, vararg children: ElementDescriptor): ElementDescriptor {
    val childrenList = ArrayList<ElementDescriptor>()
    val newElement = FunctionalElementDescriptor(component, props, childrenList)
    for (element in children) {
        childrenList.add(element)
        element.parent = newElement
    }
    return newElement
}

private class FunctionalComponent<T>(private val fn: (T) -> ElementDescriptor) : Component<T> {
    override fun render(props: T): ElementDescriptor {
        return fn(props)
    }

}

fun <T> createElement(component: (T) -> ElementDescriptor, props: T, vararg children: ElementDescriptor): ElementDescriptor {
    return createElement(FunctionalComponent(component), props, *children)
}

fun createElement(
        name: String,
        props: ObjectPropertyDescriptor = ObjectPropertyDescriptor(emptyMap()),
        vararg children: ElementDescriptor): ElementDescriptor {
    val childrenList = ArrayList<ElementDescriptor>()
    val newElement = AtomElementDescriptor(name, props, childrenList)
    for (element in children) {
        childrenList.add(element)
        element.parent = newElement
    }
    return newElement
}