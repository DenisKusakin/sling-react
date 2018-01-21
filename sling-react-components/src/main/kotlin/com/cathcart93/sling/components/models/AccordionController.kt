package com.cathcart93.sling.components.models

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.ChildResource
import org.apache.sling.models.annotations.injectorspecific.SlingObject

import javax.annotation.PostConstruct

@Model(adaptables = [Resource::class])
@ReactController(componentName = "Accordion")
class AccordionController : IReactController {

    @ReactProp
    private lateinit var  items: List<AccordionItemModel>

    @SlingObject
    private lateinit var resource: Resource

    @PostConstruct
    fun init() {
        items = resource.children.mapNotNull {
            it.adaptTo(AccordionItemModel::class.java)
        }
    }
}
