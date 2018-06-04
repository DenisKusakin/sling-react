package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.impl.builder.*

fun String.toSlideTransition(): SlideTransition {
    val types = this.split(" ").mapNotNull {
        when (it) {
            "slide" -> SlideTransitionType
            "zoom" -> ZoomTransitionType
            "fade" -> FadeTransitionType
            "spin" -> SpinTransitionType
            else -> null
        }
    }
    return SlideTransition(types)
}