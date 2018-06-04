package com.cathcart93.sling.components.models.spectacle.impl.builder

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactProp
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.StringProp

sealed class Align

object FlexStart : Align()
object Center : Align()
object FlexEnd : Align()

data class SlideAlign(val first : Align, val second: Align)

fun SlideAlign.toReactProp() : ReactProp {
    val alignToStringProp = fun(align: Align) : String {
        return when(align){
            is FlexStart -> "flex-start"
            is Center -> "center"
            is FlexEnd -> "flex-end"
        }
    }

    return StringProp("${alignToStringProp(first)} ${alignToStringProp(second)}")
}