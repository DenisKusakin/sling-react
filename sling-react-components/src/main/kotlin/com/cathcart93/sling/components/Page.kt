package com.cathcart93.sling.components

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp

@ReactController(componentName = "Root")
class Page(title: String, app: Container, scriptUrl: String, initialState: String) : IReactController {
    @ReactProp
    private val title: String = title

    @ReactProp
    private val app: Container = app

    @ReactProp("__initialState")
    private val initialState = initialState

    @ReactProp
    private val scriptUrl = scriptUrl
}