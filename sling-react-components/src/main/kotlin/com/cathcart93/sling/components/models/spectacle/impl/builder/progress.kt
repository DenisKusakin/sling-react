package com.cathcart93.sling.components.models.spectacle.impl.builder

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactProp
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.StringProp

sealed class Progress

object Pacman : Progress()
object Bar : Progress()
object Number : Progress()
object None : Progress()

fun Progress.toReactProp(): ReactProp {
    return StringProp(when (this) {
        is Pacman -> "pacman"
        is Bar -> "bar"
        is Number -> "number"
        is None -> "none"
    })
}