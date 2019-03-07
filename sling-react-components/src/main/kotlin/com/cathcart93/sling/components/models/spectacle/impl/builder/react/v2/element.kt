package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

interface Element

data class FunctionalElement<T, U>(
        val component: Component<T, U>,
        val props: T,
        val children: U
) : Element

data class ContextProviderElement<T>(
        val context: Context<T>,
        val value: T,
        val children: Element
) : Element

data class ContextConsumerElement<T>(
        val context: Context<T>,
        val children: (T) -> Element
) : Element

data class BasicElement(
        val type: String,
        val props: BasicElementProperty,
        val children: List<Element>
) : Element

//data class BasicElementWithMultipleChildren(
//        val type: String,
//        val props: BasicElementProperty,
//        val children: List<Element>
//) : Element

data class TextElement(val text: String) : Element
