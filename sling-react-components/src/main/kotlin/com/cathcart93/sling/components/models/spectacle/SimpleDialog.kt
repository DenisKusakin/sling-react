package com.cathcart93.sling.components.models.spectacle

interface SimpleDialog {
    fun getProps(): List<Property>
    val path: String

    interface Property {
        val name: String
        val value: Any?
        val title: String
    }
}