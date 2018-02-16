package com.cathcart93.sling.components.models.spectacle.api

/**
 * @author Denis_Kusakin. 2/15/2018.
 */
interface Deck {
    val colors: Map<String, String?>
    val fonts: Map<String, String?>
    val children: List<Slide>
}