package com.cathcart93.sling.components

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp

@ReactController(componentName = "Application")
class Application : IReactController {
    @ReactProp
    var content: Container? = null

    @ReactProp
    var isEditMode: Boolean = false
}