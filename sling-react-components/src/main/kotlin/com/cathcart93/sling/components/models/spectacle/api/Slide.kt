package com.cathcart93.sling.components.models.spectacle.api

import com.cathcart93.sling.core.ReactProp

interface Slide : Base{
//    @ReactProp
//    val bgColor: String?
//    @ReactProp
//    val textColor: String?
    val children: List<SlideComponent>
}