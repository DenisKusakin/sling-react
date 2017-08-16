package com.cathcart93.sling.components.models

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.components.Container
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue
import org.slf4j.LoggerFactory


/**
 * Created by Kusak on 7/15/2017.
 */
@Model(adaptables = arrayOf(Resource::class, SlingHttpServletRequest::class))
@ReactController(componentName = "Root")
class PageController : IReactController {

    @ValueMapValue(name = "title", optional = true)
    @ReactProp
    lateinit var title: String

    @SlingObject
    private lateinit var resource: Resource

    @ReactProp
    private var app: Container = Container()

    override fun init() {
        app.components = resource.children
                .map { it.adaptTo(IReactController::class.java) }
                .filterNotNull()

    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(PageController::class.java)
    }
}
