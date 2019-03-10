import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.*

fun <T, U> Component<T, U>.with(props: T, childrenBuilder: () -> U): Element {
    return createElement(this, props, childrenBuilder())//ComponentWithProps(this, props)
}

fun <T> Component<T, List<Element>>.with(props: T, childrenBlock: ChildrenBuilder.() -> Unit): Element {
    val childrenBuilder = ChildrenBuilder()
    childrenBlock(childrenBuilder)
    return createElement(this, props, childrenBuilder.list)
}

fun <T> Component<T, NoProps>.with(props: T): Element {
    return createElement(this, props, NoProps)
}

fun Component<NoProps, List<Element>>.with(childrenBlock: ChildrenBuilder.() -> Unit): Element {
    val childrenBuilder = ChildrenBuilder()
    childrenBlock(childrenBuilder)
    return createElement(this, NoProps, childrenBuilder.list)
}

fun <U> Component<NoProps, U>.with(childrenBuilder: () -> U): Element {
    return createElement(this, NoProps, childrenBuilder())
}

fun String.with(props: BasicElementProperty = BasicElementProperty()): Element {
    return createElement(this, props, emptyList())
}

fun String.with(props: BasicElementProperty, childrenBlock: ChildrenBuilder.() -> Unit): Element {
    val childrenBuilder = ChildrenBuilder()
    childrenBlock(childrenBuilder)
    return createElement(this, props, childrenBuilder.list)
}

class ChildrenBuilder {
    val list = mutableListOf<Element>()

    operator fun Element.unaryPlus() {
        list.add(this)
    }

    operator fun String.unaryPlus() {
        list.add(TextElement(this))
    }

    fun children(): List<Element> {
        return list
    }
}

//TODO: ???
//operator fun <T> Component<NoProps, T>.invoke(t: () -> T): Element {
//    return createElement(this, NoProps, t())
//}