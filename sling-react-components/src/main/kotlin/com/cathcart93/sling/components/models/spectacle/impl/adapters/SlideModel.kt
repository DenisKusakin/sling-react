package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.components.models.spectacle.api.Slide
import com.cathcart93.sling.components.models.spectacle.api.SlideComponent
import com.cathcart93.sling.components.models.spectacle.dialogs.slideDialog
import com.cathcart93.sling.core.ExcludeFromSrialization
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.google.gson.annotations.SerializedName
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue
import javax.annotation.PostConstruct

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@ReactController(Constants.SLIDE)
class SlideModel : IReactController, Slide {

    @SerializedName("__type")
    val type = Constants.SLIDE

    @SlingObject
    @ExcludeFromSrialization
    @Transient
    private lateinit var resource: Resource

    override lateinit var children: List<SlideComponent>

    @SerializedName("__dialog")
    private lateinit var dialog: Any

    @ValueMapValue
    override var bgColor: String? = null

    @ValueMapValue
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