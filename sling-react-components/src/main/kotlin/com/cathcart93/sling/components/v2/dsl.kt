package com.cathcart93.sling.components.v2

operator fun String.invoke(init: ObjectPropsBuilder.() -> Unit): BasicElement {
    val propsBuilder = ObjectPropsBuilder()
    init(propsBuilder)
    return BasicElement(this, propsBuilder.toObjectProp())
}

class ObjectPropsBuilder {
    private val map = mutableMapOf<String, PrimitiveProp>()

    infix fun String.to(value: String): StringProp {
        map[this] = StringProp(value)
        return StringProp(value)
    }

    infix fun String.to(value: Number): NumberProp {
        map[this] = NumberProp(value)
        return NumberProp(value)
    }

    infix fun String.to(value: Boolean): BooleanProp {
        map[this] = BooleanProp(value)
        return BooleanProp(value)
    }

    infix fun String.to(value: Element): ElementProp {
        map[this] = ElementProp(value)
        return ElementProp(value)
    }

    infix fun String.to(value: PrimitiveProp): PrimitiveProp {
        map[this] = value
        return value
    }

    infix fun String.to(value: List<PrimitiveProp>): ArrayProp {
        map[this] = ArrayProp(value)
        return ArrayProp(value)
    }

    infix fun String.to(init: ObjectPropsBuilder.() -> Unit): ObjectProp {
        val propsBuilder = ObjectPropsBuilder()
        init(propsBuilder)
        map[this] = propsBuilder.toObjectProp()
        return propsBuilder.toObjectProp()
    }

    infix fun String.list(listBuilder: ListBuilder.() -> Unit) {
        val builder = ListBuilder()
        listBuilder(builder)
        map[this] = ArrayProp(builder.list)
    }

    fun toObjectProp(): ObjectProp {
        return ObjectProp(map)
    }
}

class ListBuilder {
    val list = mutableListOf<PrimitiveProp>()

    infix fun item(value: String) {
        list += StringProp(value)
    }

    infix fun item(value: Number) {
        list += NumberProp(value)
    }

    infix fun item(value: PrimitiveProp) {
        list += value
    }

    fun item(value: ObjectPropsBuilder.() -> Unit) {
        val propsBuilder = ObjectPropsBuilder()
        value(propsBuilder)
        list += propsBuilder.toObjectProp()
    }
}

operator fun <T> Component<T>.invoke(init: () -> T): FunctionalElement<T> {
    return FunctionalElement(this, init())
}

fun <T> withContext(value: T, children: () -> Element): ContextProviderElement<T> {
    return ContextProviderElement(value, children())
}

fun <T> consumeContext(type: Class<T>, consumer: (T) -> Element): ContextConsumerElement<T> {
    return ContextConsumerElement(type, consumer)
}