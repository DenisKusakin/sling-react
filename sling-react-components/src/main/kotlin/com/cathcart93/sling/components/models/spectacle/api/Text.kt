package com.cathcart93.sling.components.models.spectacle.api

/**
 * @author Denis_Kusakin. 2/15/2018.
 */
interface Text : SlideComponent {
    val children: String
    val fit: Boolean
    val textColor: String?
    val lineHeight: Int
}