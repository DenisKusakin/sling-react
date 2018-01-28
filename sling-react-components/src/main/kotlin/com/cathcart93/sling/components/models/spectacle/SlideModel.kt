package com.cathcart93.sling.components.models.spectacle

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import javax.annotation.PostConstruct

@Model(adaptables = [Resource::class])
@ReactController("Slide")
class SlideModel : IReactController, Slide {

    @SlingObject
    private lateinit var resource: Resource

    @ReactProp
    private lateinit var children: List<SlideComponent>

    @PostConstruct
    fun init() {
        children = resource.children
                .mapNotNull { it.adaptTo(IReactController::class.java) }
                .filter { it is SlideComponent }
                .map { it as SlideComponent }
    }
}