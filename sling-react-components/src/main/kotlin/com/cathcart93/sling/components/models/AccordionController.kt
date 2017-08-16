package com.cathcart93.sling.components.models

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject

import javax.annotation.PostConstruct

@Model(adaptables = arrayOf(Resource::class))
@ReactController(componentName = "Accordion")
class AccordionController : IReactController {

    @ReactProp
    private var items: List<AccordionItemModel>? = null

    @SlingObject
    private var resource: Resource? = null

    @PostConstruct
    override fun init() {
        items = resource!!.children
                .map {
                    it.adaptTo(AccordionItemModel::class.java)
                }
                .filterNotNull()
    }
}
