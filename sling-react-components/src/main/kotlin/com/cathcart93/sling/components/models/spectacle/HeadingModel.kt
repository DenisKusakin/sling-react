package com.cathcart93.sling.components.models.spectacle

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@ReactController("Heading")
class HeadingModel : IReactController, SlideComponent {
    @ReactProp("children")
    @ValueMapValue
    private lateinit var text: String

    @ReactProp
    @ValueMapValue
    private var size: Int = 6

    @ReactProp
    @ValueMapValue
    private var fit: Boolean = true

    @ReactProp
    @ValueMapValue
    private var caps: Boolean = false

    @ReactProp
    @ValueMapValue
    private lateinit var textColor: String

    @ReactProp
    @ValueMapValue
    private var lineHeight: Int = 1

}