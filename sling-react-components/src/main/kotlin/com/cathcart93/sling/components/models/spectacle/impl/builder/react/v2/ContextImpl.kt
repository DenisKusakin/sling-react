package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

class ContextProviderComponent<T>(private val context: Context<T>) : Component<T, ElementDescriptor> {
    override fun render(props: T, children: ElementDescriptor): ElementDescriptor {
        return ContextProviderElementDescriptor(context, props, children)
    }
}

class ContextConsumerComponent<T>(private val context: Context<T>) : Component<NoProps, (T) -> ElementDescriptor> {
    override fun render(props: NoProps, children: (T) -> ElementDescriptor): ElementDescriptor {
        return ContextConsumerElementDescriptor(context, children)
    }

}

class ContextImpl<T> : Context<T> {
    override val provider: Component<T, ElementDescriptor>
        get() = ContextProviderComponent(this)
    override val consumer: Component<NoProps, (T) -> ElementDescriptor>
        get() = ContextConsumerComponent(this)
}