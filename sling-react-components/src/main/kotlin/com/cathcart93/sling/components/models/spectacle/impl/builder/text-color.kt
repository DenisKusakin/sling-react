package com.cathcart93.sling.components.models.spectacle.impl.builder

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactProp
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toReactProp

sealed class TextColor

data class HexColor(val color : String) : TextColor()

fun TextColor.toReactProp() : ReactProp {
    return when(this){
        is HexColor -> this.color
    }.toReactProp()
}