import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.*

fun <T, U> Component<T, U>.with(props: T, childrenBuilder: () -> U): Element {
    //val componentWithProps = ComponentWithProps(this, props)
    //childrenBuilder(componentWithProps)
    return createElement(this, props, childrenBuilder())//ComponentWithProps(this, props)
}

fun <T> Component<T, NoProps>.with(props: T): Element {
    return createElement(this, props, NoProps)
}

fun String.with(props: BasicElementProperty): Element {
    return createElement(this, props, emptyList())
}

fun String.with(props: BasicElementProperty, childrenBuilder: () -> Unit): Element {
    return createElement(this, props, emptyList())
}

class ChildrenBuilder(){
    val list = mutableListOf<Element>()

    operator fun unaryPlus(){

    }
}

//operator fun <T> Component<NoProps, (T) -> Element>.invoke(block: () -> Element) : Element {
//    TODO("")
//}

operator fun <T> Component<NoProps, T>.invoke(t: () -> T): Element {
    return createElement(this, NoProps, t())
}

//operator fun <T> Component<NoProps, T>.invoke(props: T): Element {
//    TODO("")
//}