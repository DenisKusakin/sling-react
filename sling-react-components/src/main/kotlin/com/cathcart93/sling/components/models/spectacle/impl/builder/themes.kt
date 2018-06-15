package com.cathcart93.sling.components.models.spectacle.impl.builder

object DarkTheme : ExtendedDefaultTheme() {
    override var primaryColor: String? = "white"
    override var secondaryColor: String? = "#1F2022"
    override var tertiaryColor: String? = "#03A9FC"
    override var quarternaryColor: String? = "#CECECE"
    override var primaryFont: String? = "Montserrat"
    override var secondaryFont: String? = "Helvetica"
}

object PinkTheme : ExtendedDefaultTheme() {
    override var primaryColor: String? = "#ff4081"
    override var secondaryColor: String? = "black"
    override var tertiaryColor: String? = "white"
    override var quarternaryColor: String? = "white"
    override var primaryFont: String? = "Open Sans Condensed"
    override var secondaryFont: String? = "Lobster Two"
}

object DefaultTheme : ExtendedDefaultTheme()