package com.cathcart93.sling.components.models.spectacle.api

interface Slide {
    val bgColor: String?
    val textColor: String?
    val children: List<SlideComponent>
}