package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

class ElWrapper<T>(val component: Component<T>, val props: T) {
    val children = ArrayList<ElementDescriptor>()
    fun <U> elc(component: Component<U>, props: U, block: ElWrapper<U>.() -> Unit): ElementDescriptor {
        val element = el(component, props, block)
        children.add(element)
        return element
    }

    fun toElementDescriptor(): ElementDescriptor {
        return FunctionalElementDescriptor(component, props, children)
    }
}

//fun <T> el(wrapper: ElWrapper<T>, component: Component<T>, props: T, block: ElWrapper<T>.() -> Unit): ElementDescriptor {
//    val elWrapper = ElWrapper<T>()
//    block(elWrapper)
//
//}

fun <T> el(component: Component<T>, props: T, block: ElWrapper<T>.() -> Unit): ElementDescriptor {
    val elWrapper = ElWrapper(component, props)
    block(elWrapper)
    return elWrapper.toElementDescriptor()
}

/**
 * Props DSL
 */

//fun props(block: () -> Unit): ObjectPropertyDescriptor {
//
//}

/**
 * End of DSL
 */