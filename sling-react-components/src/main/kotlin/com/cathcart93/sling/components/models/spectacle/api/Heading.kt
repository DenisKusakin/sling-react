package com.cathcart93.sling.components.models.spectacle.api

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp

interface Heading: SlideComponent  {
    val children: String
    val size: Int
    val fit: Boolean
    val caps: Boolean
    val textColor: String?
    val lineHeight: Int
}