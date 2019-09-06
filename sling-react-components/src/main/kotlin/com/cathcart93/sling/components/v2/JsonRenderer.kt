package com.cathcart93.sling.components.v2

import com.google.gson.JsonElement

interface JsonRenderer {
    fun render(element: Element) : JsonElement
}