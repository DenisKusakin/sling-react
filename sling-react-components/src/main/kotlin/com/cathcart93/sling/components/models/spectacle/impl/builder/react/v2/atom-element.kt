package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

sealed class AtomProperty

data class StringProperty(val value: String) : AtomProperty()
data class BooleanProperty(val value: Boolean) : AtomProperty()
data class NumberProperty(val value: Number) : AtomProperty()
data class ArrayProperty(val value: List<AtomProperty>) : AtomProperty()
data class ObjectProperty(val value: Map<String, AtomProperty>) : AtomProperty()

class PropsBuilder {
    private val map = mutableMapOf<String, AtomProperty>()

    infix fun String.to(value: String): StringProperty {
        val prop = StringProperty(value)
        map[this] = prop
        return prop
    }

    infix fun String.to(value: Boolean): BooleanProperty {
        val prop = BooleanProperty(value)
        map[this] = prop
        return prop
    }

    infix fun String.to(value: Number): NumberProperty {
        val prop = NumberProperty(value)
        map[this] = prop
        return prop
    }

    infix fun String.to(prop: AtomProperty): AtomProperty {
        map[this] = prop
        return prop
    }

    fun toObjectProperty(): ObjectProperty {
        return ObjectProperty(map)
    }
}

class ArrayPropBuilder {
    private val list = mutableListOf<AtomProperty>()

    operator fun AtomProperty.unaryPlus() {
        list.add(this)
    }

    fun toArrayProperty(): ArrayProperty {
        return ArrayProperty(list)
    }
}

fun props(block: PropsBuilder.() -> Unit): ObjectProperty {
    val propsBuilder = PropsBuilder()
    block(propsBuilder)
    return propsBuilder.toObjectProperty()
}

fun list(block: ArrayPropBuilder.() -> Unit): ArrayProperty {
    val arrayPropBuilder = ArrayPropBuilder()
    block(arrayPropBuilder)
    return arrayPropBuilder.toArrayProperty()
}