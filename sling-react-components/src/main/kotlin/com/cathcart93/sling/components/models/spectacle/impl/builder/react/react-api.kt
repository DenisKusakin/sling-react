package com.cathcart93.sling.components.models.spectacle.impl.builder.react

import com.google.gson.Gson

interface IReactElement {
    val name: String
    val props: Map<String, ReactProp>
    val children: ReactChildren
}

data class ReactElement(
        override val name: String,
        override val props: Map<String, ReactProp> = mutableMapOf(),
        override val children: ReactChildren = NoChildren) : IReactElement, ReactProp {
    constructor(name: String, props: Map<String, ReactProp> = mutableMapOf(), children: ReactProp) : this(name, props, OnlyChild(children))
    constructor(name: String, props: Map<String, ReactProp> = mutableMapOf(), children: List<ReactElement>) : this(name, props, ChildrenList(children))
}

sealed class ReactChildren
data class OnlyChild(val child: ReactProp) : ReactChildren()
data class ChildrenList(val children: List<ReactProp>) : ReactChildren()
object NoChildren : ReactChildren()

interface ReactProp
data class StringProp(val value: String) : ReactProp
data class BooleanProp(val value: Boolean) : ReactProp
data class NumberProp(val value: Number) : ReactProp
data class ElementProp(val value: ReactElement) : ReactProp
data class ObjectProps(val value: Map<String, ReactProp?>) : ReactProp
data class ArrayProp(val value: List<ReactProp>) : ReactProp

fun Boolean.toReactProp(): ReactProp {
    return BooleanProp(this)
}

fun String.toReactProp(): ReactProp {
    return StringProp(this)
}

fun Number.toReactProp(): ReactProp {
    return NumberProp(this)
}

fun ReactElement.toJson(): String {
    fun reactElementToMap(element: ReactElement): Map<String, Any?> {
        fun reactPropToValue(prop: ReactProp): Any? {
            return when (prop) {
                is StringProp -> prop.value
                is BooleanProp -> prop.value
                is NumberProp -> prop.value
                is ElementProp -> reactElementToMap(prop.value)
                is ReactElement -> reactElementToMap(prop)
                is ObjectProps -> prop.value.mapValues {
                    val (_, value) = it
                    if (value == null) {
                        return null
                    }
                    reactPropToValue(value)
                }
                is ArrayProp -> prop.value.map { reactPropToValue(it) }
                else -> this
            }
        }

        val map = mutableMapOf<String, Any?>("__type" to element.name)
        element.props.forEach { t: String, u: ReactProp ->
            map[t] = reactPropToValue(u)
        }

        map["children"] = when (element.children) {
            is ChildrenList -> element.children.children.map { reactPropToValue(it) }
            is OnlyChild -> reactPropToValue(element.children.child)
            is NoChildren -> null
        }

        return map
    }
    return Gson().toJson(reactElementToMap(this))
}