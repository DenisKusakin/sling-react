package com.cathcart93.sling.components.models.spectacle.impl.builder

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactProp
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toReactProp

sealed class TextAlign

object CenterAlign : TextAlign()
object JustifyAlign : TextAlign()
object LeftAlign : TextAlign()
object RightAlign : TextAlign()
object AutoAlign : TextAlign()
object InheritAlign : TextAlign()
object StartAlign : TextAlign()
object EndAlign : TextAlign()

fun TextAlign.toReactProp(): ReactProp {
    return when (this) {
        is CenterAlign -> "center"
        is JustifyAlign -> "justify"
        is LeftAlign -> "left"
        is RightAlign -> "right"
        is InheritAlign -> "inherit"
        is AutoAlign -> "auto"
        is StartAlign -> "start"
        is EndAlign -> "end"
    }.toReactProp()
}