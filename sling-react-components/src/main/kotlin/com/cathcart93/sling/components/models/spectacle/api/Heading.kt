package com.cathcart93.sling.components.models.spectacle.api

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp

@ReactController(Constants.HEADING)
interface Heading: IReactController, SlideComponent  {
    @ReactProp
    val children: String

    @ReactProp
    val size: Int

    @ReactProp
    val fit: Boolean

    @ReactProp
    val caps: Boolean

    @ReactProp
    val textColor: String?

    @ReactProp
    val lineHeight: Int
}