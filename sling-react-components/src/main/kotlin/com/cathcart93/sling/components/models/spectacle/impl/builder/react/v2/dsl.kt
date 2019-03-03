package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

class ElWrapper<T>(val component: Component<T>, val props: T) {
    val children = ArrayList<ElementDescriptor>()
//    fun <U> elc(component: ComponentWithProps<U>, props: U, block: ElWrapper<U>.() -> Unit): ElementDescriptor {
//        val element = el(component, props, block)
//        children.add(element)
//        return element
//    }

    //fun <U> with(com)

    fun toElementDescriptor(): ElementDescriptor {
        return FunctionalElementWithPropsAndChildrenDescriptor(component, props, children)
    }
}

class AtomElWrapper(val componentName: String, val props: ObjectPropertyDescriptor) {
    val children = ArrayList<ElementDescriptor>()

    fun toElementDescriptor(): ElementDescriptor {
        return AtomElementDescriptor(componentName, props, children)
    }
}

fun <T, U> ElWrapper<T>.el(component: Component<U>, props: U, block: ElWrapper<U>.() -> Unit): ElementDescriptor {
    val newWrapper = ElWrapper(component, props)
    block(newWrapper)
    val newElement = newWrapper.toElementDescriptor()
    this.children.add(newElement)
    return newElement
    //this.children.ad
}

//fun <T> AtomElWrapper.el(component: ComponentWithProps<T>, props: T, block: ElWrapper<T>.() -> Unit): ElementDescriptor {
//    val newWrapper = ElWrapper(component, props)
//    block(newWrapper)
//    val newElement = newWrapper.toElementDescriptor()
//    this.children.add(newElement)
//    return newElement
//}

//fun <T> el(wrapper: ElWrapper<T>, component: Component<T>, props: T, block: ElWrapper<T>.() -> Unit): ElementDescriptor {
//    val elWrapper = ElWrapper<T>()
//    block(elWrapper)
//
//}

fun <T> Component<T>.with(props: T, block: ElWrapper<T>.() -> Unit): ElementDescriptor {
    val elWrapper = ElWrapper(this, props)
    block(elWrapper)
    return elWrapper.toElementDescriptor()
}

fun <T> el(component: Component<T>, props: T, block: ElWrapper<T>.() -> Unit): ElementDescriptor {
    val elWrapper = ElWrapper(component, props)
    block(elWrapper)
    return elWrapper.toElementDescriptor()
}

//fun el(componentName: String, props: ObjectPropertyDescriptor, block: AtomElWrapper.() -> Unit): ElementDescriptor {
//
//}

/**
 * Props DSL
 */

//class ObjectPropsBuilder {
//    val map = mutableMapOf<String, PropertyDescriptor>()
//    fun toPropertyDescriptor(): ObjectPropertyDescriptor {
//        TODO("")
//    }
//
//    fun String.to(value: String) {
//        map[this] = value
//    }
//}
//
//fun props(block: ObjectPropsBuilder.() -> Unit): ObjectPropertyDescriptor {
//    val propsBuilder = ObjectPropsBuilder()
//    block(propsBuilder)
//    return propsBuilder.toPropertyDescriptor()
//}

/**
 * End of DSL
 */