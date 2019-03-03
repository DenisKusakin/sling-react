package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

interface Component<T> {
    fun render(props: T): ElementDescriptor
}