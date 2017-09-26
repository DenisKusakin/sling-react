package com.cathcart93.sling.components

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp

@ReactController(componentName = "Root")
class Root(private @ReactProp var title: String, private @ReactProp var app: Application): IReactController