package com.cathcart93.sling.components.models.spectacle.api

/**
 * @author Denis_Kusakin. 2/15/2018.
 */
interface Deck {
    val primaryColor: String?
    val secondaryColor: String?
    val tertiaryColor: String?
    val quarternaryColor: String?
    val primaryFont: String?
    val secondaryFont: String?
    val colors: Map<String, String?>
    val fonts: Map<String, String?>
    val children: List<Slide>
}