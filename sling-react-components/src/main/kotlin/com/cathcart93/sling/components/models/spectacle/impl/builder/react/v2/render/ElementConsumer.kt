package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.ElementDescriptor

interface ElementConsumer<T> {
    fun render(element: ElementDescriptor): T
}