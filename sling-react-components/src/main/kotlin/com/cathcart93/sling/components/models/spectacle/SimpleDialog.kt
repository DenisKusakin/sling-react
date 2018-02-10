package com.cathcart93.sling.components.models.spectacle

interface SimpleDialog {
//    fun getProps(): List<Property>
    val props: List<Property>
    val path: String

    interface Property {
        val name: String
        val value: Any?
        val title: String
        val type: String
    }

    class TextProperty(override val name: String, override val value: Any?, override val title: String) : Property {
        override val type = "text"
    }

    class CheckboxProperty(override val name: String, override val value: Boolean, override val title: String) : Property {
        override val type = "checkbox"
    }

    class SelectProperty(override val name: String, override val value: String?,
                         val options: List<SelectPropertyOption>, override val title: String) : Property {
        override val type = "select"

        data class SelectPropertyOption(val label: String, val value: String)
    }
}