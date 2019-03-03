package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

class ContextProviderComponent<T>(private val context: Context<T>) : Component<T> {
    override fun render(props: T, children: List<ElementDescriptor>): ElementDescriptor {
        return ContextProviderElementDescriptor(context, props, children)
    }
}

class ContextConsumerComponent<T>(private val context: Context<T>) : Component<(T) -> ElementDescriptor> {
    override fun render(props: (T) -> ElementDescriptor, children: List<ElementDescriptor>): ElementDescriptor {
        return ContextConsumerElementDescriptor(context, props, children)
    }

}

class ContextImpl<T> : Context<T> {
    override val provider: Component<T>
        get() = ContextProviderComponent(this)
    override val consumer: Component<(T) -> ElementDescriptor>
        get() = ContextConsumerComponent(this)
}