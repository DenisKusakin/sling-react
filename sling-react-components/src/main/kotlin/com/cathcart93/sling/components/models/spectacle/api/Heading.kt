package com.cathcart93.sling.components.models.spectacle.api

interface Heading: Base, SlideComponent  {
    val children: String
    val size: Int
    val fit: Boolean
    val lineHeight: Int
}