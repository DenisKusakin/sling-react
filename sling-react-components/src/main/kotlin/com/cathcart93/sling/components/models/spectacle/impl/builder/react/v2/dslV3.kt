package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

operator fun <T, U> Component<T, U>.invoke(props: T, children: () -> U): Element {
    return createElement(this, props, children())
}

operator fun <T> Component<NoProps, T>.invoke(children: () -> T): Element {
    return createElement(this, NoProps, children())
}

operator fun <T> Component<T, NoProps>.invoke(props: T): Element {
    return createElement(this, props, NoProps)
}