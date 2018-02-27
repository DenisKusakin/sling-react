package com.cathcart93.sling.components.models.spectacle.api

interface Slide {
    var bgColor: String?
    var textColor: String?
    var children: List<SlideComponent>
}