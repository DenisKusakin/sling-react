package com.cathcart93.sling.components.models.spectacle.impl.builder

/**
 * @author Denis_Kusakin. 6/15/2018.
 */

sealed class CodeTheme

object LightCodeTheme : CodeTheme()
object DarkCodeTheme : CodeTheme()
object ExternalCodeTheme : CodeTheme()

fun CodeTheme.toReactValue(): String {
    return when (this) {
        is LightCodeTheme -> "light"
        is DarkCodeTheme -> "dark"
        is ExternalCodeTheme -> "external"
    }
}