package com.cathcart93.sling.components.models.spectacle.api

import com.cathcart93.sling.core.ReactProp

interface Base {
    //@ReactProp
    val italic: Boolean?
    //@ReactProp
    val bold: Boolean?
    //@ReactProp
    val caps: Boolean?
    //@ReactProp
    val margin: Int?
    //@ReactProp
    val padding: Int?
    //@ReactProp
    val textColor: String?
    //@ReactProp
    val textSize: String?
    //@ReactProp
    val textAlign: String?
    //@ReactProp
    val textFont: String?
    //@ReactProp
    val bgColor: String?
    //@ReactProp
    val bgDarken: Float?
}