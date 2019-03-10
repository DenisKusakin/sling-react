package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

fun <T, U> createElement(component: Component<T, U>, props: T, children: U): Element {
    return FunctionalElement(component, props, children)
}

object NoProps

//fun <T> createElement(
//        component: (T, List<Element>) -> Element,
//        props: T,
//        vararg childrenProp: Element): Element {
//    return createElement(createComponent(component), props, childrenProp.toList())
//}

fun createElement(
        type: String,
        props: BasicElementProperty,
        children: List<Element> = emptyList()
): Element {
    return BasicElement(type, props, children)
}

//fun createElement(
//        type: String,
//        props: BasicElementProperty,
//        childrenProp: List<Element>
//) : Element{
//    return BasicElementWithMultipleChildren(type, props, childrenProp)
//}