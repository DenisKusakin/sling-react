package com.cathcart93.sling.components.models.spectacle.api

import com.cathcart93.sling.core.ReactProp

/**
 * @author Denis_Kusakin. 2/15/2018.
 */
interface Text : SlideComponent, Base {
//    @ReactProp
    val children: String
//    @ReactProp
    val fit: Boolean
//    @ReactProp
//    val textColor: String?
//    @ReactProp
    val lineHeight: Int
}