package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

class ContextProviderComponent<T>(private val context: Context<T>) : Component<T> {
    override fun render(props: T): ElementDescriptor {
        return ContextProviderElementDescriptor(context, props)
    }
}

class ContextConsumerComponent<T>(private val context: Context<T>) : Component<(T) -> ElementDescriptor> {
    override fun render(props: (T) -> ElementDescriptor): ElementDescriptor {
        return ContextConsumerElementDescriptor(context, props)
    }

}

class ContextImpl<T> : Context<T> {
    override val provider: Component<T>
        get() = ContextProviderComponent(this)
    override val consumer: Component<(T) -> ElementDescriptor>
        get() = ContextConsumerComponent(this)
}