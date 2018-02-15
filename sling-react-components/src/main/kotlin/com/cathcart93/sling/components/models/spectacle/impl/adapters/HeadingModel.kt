package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.components.models.spectacle.api.SimpleDialog
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue
import javax.annotation.PostConstruct
import com.cathcart93.sling.components.models.spectacle.api.Heading
import com.cathcart93.sling.components.models.spectacle.dialogs.headingDialog

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@ReactController(Constants.HEADING)
class HeadingModel : IReactController, Heading {
    @ValueMapValue(name = "text")
    override lateinit var children: String

    @ReactProp
    @ValueMapValue
    override var size: Int = 6

    @ReactProp
    @ValueMapValue
    override var fit: Boolean = true

    @ReactProp
    @ValueMapValue
    override var caps: Boolean = false

    @ReactProp
    @ValueMapValue
    override var textColor: String? = null

    @ReactProp
    @ValueMapValue
    override var lineHeight: Int = 1

    @ReactProp("__dialog")
    private lateinit var dialog: SimpleDialog

    @ReactProp("__dialog_type")
    private val dialogType = "dialogs/SimpleDialog"

    @SlingObject
    private lateinit var resource: Resource

    @PostConstruct
    fun init() {
        dialog = headingDialog(resource)
    }

}