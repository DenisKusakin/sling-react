package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

class ContextProviderComponent<T>(private val context: Context<T>) : Component<T, Element> {
    override fun render(props: T, children: Element): Element {
        return ContextProviderElement(context, props, children)
    }
}

class ContextConsumerComponent<T>(private val context: Context<T>) : Component<NoProps, (T) -> Element> {
    override fun render(props: NoProps, children: (T) -> Element): Element {
        return ContextConsumerElement(context, children)
    }

}

class ContextImpl<T> : Context<T> {
    private val providerComponent = ContextProviderComponent(this)
    private val consumerComponent = ContextConsumerComponent(this)

    override val provider: Component<T, Element>
        get() = providerComponent
    override val consumer: Component<NoProps, (T) -> Element>
        get() = consumerComponent
}