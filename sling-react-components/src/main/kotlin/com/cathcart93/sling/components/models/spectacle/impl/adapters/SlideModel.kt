package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.components.models.spectacle.api.Slide
import com.cathcart93.sling.components.models.spectacle.api.SlideComponent
import com.cathcart93.sling.components.models.spectacle.dialogs.slideDialog
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue
import javax.annotation.PostConstruct

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@ReactController(Constants.SLIDE)
class SlideModel : IReactController, Slide {

    @SlingObject
    private lateinit var resource: Resource

    @ReactProp
    override lateinit var children: List<SlideComponent>

    @ReactProp("__dialog")
    private lateinit var dialog: Any

    @ValueMapValue
    @ReactProp
    override var bgColor: String? = null

    @ValueMapValue
    @ReactProp
    override var textColor: String? = null

    @PostConstruct
    fun init() {
        children = resource.children
                .mapNotNull { it.adaptTo(IReactController::class.java) }
                .filter { it is SlideComponent }
                .map { it as SlideComponent }

        dialog = slideDialog(resource)
    }
}