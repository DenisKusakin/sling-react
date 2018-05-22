package com.cathcart93.sling.components.models.spectacle.api

interface Image : Base, SlideComponent {
    val height: String?
    val width: String?
    val src: String?
}