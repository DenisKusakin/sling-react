package com.cathcart93.sling.components

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.google.gson.annotations.SerializedName

@ReactController(componentName = "Root")
class Page(title: String, app: Container, scriptUrl: String, initialState: String) : IReactController {
    @SerializedName("__type")
    val type = "Root"
    private val title: String = title

    private val app: Container = app

    @SerializedName("__initialState")
    private val initialState = initialState

    private val scriptUrl = scriptUrl
}