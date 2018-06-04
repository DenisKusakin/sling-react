package com.cathcart93.sling.components.models.spectacle.impl.builder

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ArrayProp
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactProp
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.StringProp

sealed class TransitionType

object SlideTransitionType : TransitionType()
object ZoomTransitionType : TransitionType()
object FadeTransitionType : TransitionType()
object SpinTransitionType : TransitionType()

data class SlideTransition(val value: List<TransitionType>)

fun SlideTransition.toReactProp(): ReactProp {
    val transitionTypeToString = fun(x: TransitionType): String {
        return when (x) {
            is SlideTransitionType -> "slide"
            is ZoomTransitionType -> "zoom"
            is FadeTransitionType -> "fade"
            is SpinTransitionType -> "spin"
        }
    }

    return ArrayProp(value.map { StringProp(transitionTypeToString(it)) })
}