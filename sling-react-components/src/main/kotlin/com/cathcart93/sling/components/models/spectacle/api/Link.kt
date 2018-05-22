package com.cathcart93.sling.components.models.spectacle.api

interface Link : SlideComponent {
    val href: String?
    val target: String?
    val children: String?
}