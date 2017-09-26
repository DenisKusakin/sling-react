package com.cathcart93.sling.components.models

import com.cathcart93.sling.components.Application
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.components.Container
import com.cathcart93.sling.components.Root
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

    @SlingObject
    private lateinit var request: SlingHttpServletRequest

    @ReactProp
    private val app: Application = Application()

//    @ReactProp("__initialState")
//    private lateinit var initialState: Root

    override fun init() {
        val content = Container()
        content.components = resource.children
                .map { it.adaptTo(IReactController::class.java) }
                .filterNotNull()
        app.content = content
        app.isEditMode = request.getParameter("isEdit") != null

//        initialState = Root(title, app)
    }
}
