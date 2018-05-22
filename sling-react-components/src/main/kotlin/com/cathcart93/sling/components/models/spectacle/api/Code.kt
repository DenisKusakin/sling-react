package com.cathcart93.sling.components.models.spectacle.api

import com.cathcart93.sling.core.ReactProp

/**
 * @author Denis_Kusakin. 2/15/2018.
 */
interface Code : SlideComponent, Base{
//    @ReactProp
    val source: String?
//    @ReactProp
    val lang: String?
}