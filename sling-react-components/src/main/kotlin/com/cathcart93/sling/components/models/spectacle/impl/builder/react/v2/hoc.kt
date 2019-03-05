package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

fun <T, U> createComponent(fn: (T, U) -> ElementDescriptor): Component<T, U> {
    return FunctionalComponent(fn)
}

fun <T> createComponent(fn: (T) -> ElementDescriptor): Component<T, NoProps> {
    return createComponent { props, _ ->
        fn(props)
    }
}

fun createComponent(fn: () -> ElementDescriptor): Component<NoProps, NoProps> {
    return createComponent<NoProps> { fn() }
}

class FunctionalComponent<T, U>(private val fn: (T, U) -> ElementDescriptor) : Component<T, U> {
    override fun render(props: T, children: U): ElementDescriptor {
        return fn(props, children)
    }
}