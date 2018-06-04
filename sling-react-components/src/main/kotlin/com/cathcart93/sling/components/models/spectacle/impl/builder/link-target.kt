package com.cathcart93.sling.components.models.spectacle.impl.builder

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactProp
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toReactProp

sealed class LinkTarget

object Blank : LinkTarget()
object Self : LinkTarget()
object Parent : LinkTarget()
object Top : LinkTarget()

fun LinkTarget.toReactProp(): ReactProp {
    return when (this) {
        is Blank -> "_blank"
        is Self -> "_self"
        is Parent -> "_parent"
        is Top -> "_top"
    }.toReactProp()
}