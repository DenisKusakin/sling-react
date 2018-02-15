package com.cathcart93.sling.components.models.spectacle.api

interface Heading  {
    val children: String
    val size: Int
    val fit: Boolean
    val caps: Boolean
    val textColor: String
    val lineHeight: Int
}