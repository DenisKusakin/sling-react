package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

fun <T> createElement(component: Component<T>, props: T, vararg children: ElementDescriptor): ElementDescriptor {
    val childrenList = ArrayList<ElementDescriptor>()
    val newElement = FunctionalElementWithPropsAndChildrenDescriptor(component, props, childrenList)
    for (element in children) {
        childrenList.add(element)
        element.parent = newElement
    }
    return newElement
}

private class FunctionalComponentWithPropsAndChildren<T>(
        private val fn: (T, List<ElementDescriptor>) -> ElementDescriptor) : Component<T> {
    override fun render(props: T, children: List<ElementDescriptor>): ElementDescriptor {
        return fn(props, children)
    }
}

object NoProps

private class FunctionalComponentWithoutProps(
        private val fn: () -> ElementDescriptor) : Component<NoProps> {
    override fun render(props: NoProps, children: List<ElementDescriptor>): ElementDescriptor {
        return fn()
    }
}

private class FunctionalComponentsWithProps<T>(
        private val fn: (T) -> ElementDescriptor
) : Component<T> {
    override fun render(props: T, children: List<ElementDescriptor>): ElementDescriptor {
        return fn(props)
    }
}

/**
 *
 */
fun <T> createElement(
        component: (T, List<ElementDescriptor>) -> ElementDescriptor,
        props: T,
        vararg children: ElementDescriptor): ElementDescriptor {
    return createElement(FunctionalComponentWithPropsAndChildren(component), props, *children)
}

fun <T> createElement(
        component: (T) -> ElementDescriptor,
        props: T
) : ElementDescriptor {
    return createElement(FunctionalComponentsWithProps(component), props)
}

fun createElement(
        component: () -> ElementDescriptor,
        vararg children: ElementDescriptor): ElementDescriptor {
    return createElement(FunctionalComponentWithoutProps(component), NoProps, *children)
}



fun createElement(
        name: String,
        props: Any? = null,
        vararg children: ElementDescriptor): ElementDescriptor {
    val childrenList = ArrayList<ElementDescriptor>()
    val newElement = AtomElementDescriptor(name, props, childrenList)
    for (element in children) {
        childrenList.add(element)
        element.parent = newElement
    }
    return newElement
}

fun createElement(
        name: String,
        props: Any? = null,
        children: List<ElementDescriptor>): ElementDescriptor {
    return createElement(name, props, *(children.toTypedArray()))
}