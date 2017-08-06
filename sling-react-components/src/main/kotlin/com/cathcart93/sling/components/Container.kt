package com.cathcart93.sling.components

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp

@ReactController(componentName = "Container")
class Container : IReactController {
    @ReactProp
    var components: List<Any>? = null
}
