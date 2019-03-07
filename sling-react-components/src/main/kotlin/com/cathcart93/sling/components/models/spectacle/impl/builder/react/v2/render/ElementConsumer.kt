package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.Element

interface ElementConsumer {
    fun render(element: Element): Element
}