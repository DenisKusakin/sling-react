package com.cathcart93.sling.components.models.spectacle.impl.builder.react

import com.google.gson.Gson

interface IReactElement {
    val name: String
    val props: Map<String, ReactProp>
    val children: List<ReactProp>?
}

data class ReactElement(
        override val name: String,
        override val props: Map<String, ReactProp> = mutableMapOf(),
        override val children: List<ReactProp>? = null) : IReactElement, ReactProp

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

        map["children"] = element.children?.map { reactPropToValue(it) }

        return map
    }
    return Gson().toJson(reactElementToMap(this))
}

fun ReactElement.addProp(propName: String, provValue: ReactProp): ReactElement {
    val currentProps = this.props
    val newProps = mutableMapOf(propName to provValue)
    newProps.putAll(currentProps)
    newProps[propName] = provValue
    return this.copy(props = newProps)
}

fun ReactElement.addProps(props: Map<String, ReactProp>): ReactElement {
    val currentProps = this.props
    val newProps = mutableMapOf<String, ReactProp>()
    newProps.putAll(currentProps)
    newProps.putAll(props)
    return this.copy(props = newProps)
}