package com.cathcart93.sling.components.models

import com.cathcart93.sling.components.Container
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject

import javax.annotation.PostConstruct

@Model(adaptables = arrayOf(Resource::class, SlingHttpServletRequest::class))
@ReactController(componentName = "SelfUpdatableContainer")
class SelfUpdatableContainerController : IReactController {

//    @ReactProp(name = "initialContent")
//    private var content: Container = Container()

    @ReactProp
    private var isEditMode: Boolean = false

    @ReactProp
    private lateinit var contentPath: String

    @SlingObject
    private lateinit var resource: Resource

    @PostConstruct
    fun init() {
        contentPath = resource.path
        isEditMode = true//request.getParameter("isEdit") != null
        val items = resource.children.mapNotNull {
            it.adaptTo(IReactController::class.java)
        }
//        content.components = items
    }
}
