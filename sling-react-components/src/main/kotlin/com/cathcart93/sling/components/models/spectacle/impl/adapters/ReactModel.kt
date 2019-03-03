package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement

interface ReactModel {
    fun render(context: RenderContext): ReactElement
}