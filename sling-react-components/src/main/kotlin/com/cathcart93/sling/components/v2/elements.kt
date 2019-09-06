package com.cathcart93.sling.components.v2

sealed class Element

data class BasicElement(val type: String, val props: ObjectProp) : Element()
data class FunctionalElement<T>(val component: ComponentV2<T>, val props: T) : Element()
data class ContextProviderElement<T>(val context: T, val children: Element) : Element()
data class ContextConsumerElement<T>(val contextType: Class<T>, val consumer: (T) -> Element) : Element()

sealed class PrimitiveProp

data class StringProp(val value: String) : PrimitiveProp()
data class NumberProp(val value: Number) : PrimitiveProp()
data class BooleanProp(val value: Boolean) : PrimitiveProp()
data class ArrayProp(val value: List<PrimitiveProp>) : PrimitiveProp()
data class ObjectProp(val value: Map<String, PrimitiveProp>) : PrimitiveProp()
data class ElementProp(val value: Element) : PrimitiveProp()