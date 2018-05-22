package com.cathcart93.sling.components.models.spectacle.api

import com.cathcart93.sling.core.ReactProp

/**
 * @author Denis_Kusakin. 2/15/2018.
 */
interface Deck {
//    @ReactProp
    val colors: Map<String, String?>
//    @ReactProp
    val fonts: Map<String, String?>
    val children: List<Slide>
}