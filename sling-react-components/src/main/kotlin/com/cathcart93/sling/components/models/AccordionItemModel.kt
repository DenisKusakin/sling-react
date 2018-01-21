package com.cathcart93.sling.components.models

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.components.Container
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

import javax.annotation.PostConstruct

@Model(adaptables = [(Resource::class)], adapters = [AccordionItemModel::class])
class AccordionItemModel : IReactController{

    @ValueMapValue(name = "title")
    @ReactProp
    private lateinit var title: String

    @SlingObject
    private lateinit var resource: Resource

    @ReactProp
    private val body = Container()

    @PostConstruct fun init() {
//        body.components = resource.children.mapNotNull { it.adaptTo(IReactController::class.java) }
    }
}
