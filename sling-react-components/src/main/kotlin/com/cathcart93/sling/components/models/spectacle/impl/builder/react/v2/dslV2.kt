//package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2
//
//operator fun String.invoke(props: BasicElementProperty, childrenBlock: ChildrenBuilderV2.() -> Unit): Element {
//    val childrenBuilder = ChildrenBuilderV2()
//    childrenBlock(childrenBuilder)
//    return createElement(this, props, childrenBuilder.children)
//}
//
//operator fun String.invoke(childrenBlock: ChildrenBuilderV2.() -> Unit): Element {
//    val childrenBuilder = ChildrenBuilderV2()
//    childrenBlock(childrenBuilder)
//    return createElement(this, BasicElementProperty(), childrenBuilder.children)
//}
//
//
//operator fun String.invoke(): Element {
//    return createElement(this, BasicElementProperty(), emptyList())
//}
//
//
//operator fun <T, U> Component<T, U>.invoke(props: T, children: U): Element {
//    return createElement(this, props, children)
//}
//
//operator fun <T> Component<T, List<Element>>.invoke(props: T, childrenBlock: ChildrenBuilderV2.() -> Unit): Element {
//    val childrenBuilder = ChildrenBuilderV2()
//    childrenBlock(childrenBuilder)
//    return createElement(this, props, childrenBuilder.children)
//}
//
//operator fun <T> Component<T, NoProps>.invoke(props: T): Element {
//    return createElement(this, props, NoProps)
//}
//
//operator fun Component<NoProps, NoProps>.invoke(): Element {
//    return createElement(this, NoProps, NoProps)
//}
//
//operator fun Component<NoProps, List<Element>>.invoke(block: ChildrenBuilderV2.() -> Unit): Element {
//    val childrenBuilderV2 = ChildrenBuilderV2()
//    block(childrenBuilderV2)
//    return createElement(this, NoProps, childrenBuilderV2.children)
//}
//
//class ChildrenBuilderV2 {
//    val children = mutableListOf<Element>()
//
//    operator fun <T, U> Component<T, U>.invoke(props: T, child: U): Element {
//        val newElement = createElement(this, props, child)
//        children.add(newElement)
//        return newElement
//    }
//
//    operator fun <T> Component<T, NoProps>.invoke(props: T): Element {
//        val newElement = createElement(this, props, NoProps)
//        children.add(newElement)
//        return newElement
//    }
//
//    operator fun <T> Component<T, List<Element>>.invoke(props: T, childrenBlock: ChildrenBuilderV2.() -> Unit): Element {
//        val childrenBuilder = ChildrenBuilderV2()
//        childrenBlock(childrenBuilder)
//        val newElement = createElement(this, props, childrenBuilder.children)
//        children.add(newElement)
//        return newElement
//    }
//
//    operator fun Component<NoProps, NoProps>.invoke(): Element {
//        val newElement = createElement(this, NoProps, NoProps)
//        children.add(newElement)
//        return newElement
//    }
//
//    operator fun Component<NoProps, List<Element>>.invoke(block: ChildrenBuilderV2.() -> Unit): Element {
//        val childrenBuilderV2 = ChildrenBuilderV2()
//        block(childrenBuilderV2)
//        val newElement = createElement(this, NoProps, childrenBuilderV2.children)
//        children.add(newElement)
//        return newElement
//    }
//
//    operator fun Element.unaryPlus() {
//        children.add(this)
//    }
//
//    operator fun String.invoke(props: BasicElementProperty, childrenBlock: ChildrenBuilderV2.() -> Unit): Element {
//        val childrenBuilder = ChildrenBuilderV2()
//        childrenBlock(childrenBuilder)
//        val newElement = createElement(this, props, childrenBuilder.children)
//        children.add(newElement)
//        return newElement
//    }
//
//    operator fun String.invoke(childrenBlock: ChildrenBuilderV2.() -> Unit): Element {
//        val childrenBuilder = ChildrenBuilderV2()
//        childrenBlock(childrenBuilder)
//        val newElement = createElement(this, BasicElementProperty(), childrenBuilder.children)
//        children.add(newElement)
//        return newElement
//    }
//
//    operator fun String.invoke(): Element {
//        val newElement = createElement(this, BasicElementProperty(), emptyList())
//        children.add(newElement)
//        return newElement
//    }
//
//    operator fun <T> ContextConsumerComponent<T>.invoke(block: (T) -> Element): Element {
//        val newElement = createElement(this, NoProps, block)
//        children.add(newElement)
//        return newElement
//    }
//}