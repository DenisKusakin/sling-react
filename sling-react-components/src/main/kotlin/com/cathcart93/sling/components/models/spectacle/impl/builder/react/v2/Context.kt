package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

interface Context<T> {
    val provider: Component<T, ElementDescriptor>
    val consumer: Component<NoProps, (T) -> ElementDescriptor>
}