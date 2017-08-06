package com.cathcart93.sling.components.models

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.components.Container
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

import javax.annotation.PostConstruct

@Model(adaptables = arrayOf(Resource::class))
class AccordionItemModel : IReactController {

    @ValueMapValue(name = "title")
    @ReactProp
    private val title: String? = null

    @SlingObject
    private val resource: Resource? = null

    @ReactProp
    private val body = Container()

    @PostConstruct
    override fun init() {
        body.components = resource!!.children
                .map { it.adaptTo(IReactController::class.java) }
                .filterNotNull()
    }
}
