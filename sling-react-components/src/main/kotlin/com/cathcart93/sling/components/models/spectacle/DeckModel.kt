package com.cathcart93.sling.components.models.spectacle

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import javax.annotation.PostConstruct

@Model(adaptables = [Resource::class])
@ReactController("Deck")
class DeckModel : IReactController {

    @SlingObject
    private lateinit var resource: Resource

    @ReactProp("children")
    private lateinit var slides: List<Slide>

    @PostConstruct
    fun init() {
        slides = resource.children.mapNotNull { it.adaptTo(SlideModel::class.java) }
    }
}