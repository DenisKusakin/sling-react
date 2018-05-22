package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.*
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue
import javax.annotation.PostConstruct
import com.cathcart93.sling.components.models.spectacle.dialogs.headingDialog
import com.cathcart93.sling.components.models.spectacle.dialogs.imageDialog
import com.cathcart93.sling.components.models.spectacle.dialogs.linkDialog

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@ReactController(Constants.IMAGE)
class ImageModel : IReactController, Image, BaseModel() {

    @ReactProp
    @ValueMapValue
    override var src: String? = ""

    @ReactProp
    @ValueMapValue
    override var height: String? = null

    @ReactProp
    @ValueMapValue
    override val width: String? = null

    @ReactProp("__dialog")
    lateinit var dialog: SimpleDialog

    @ReactProp("__dialog_type")
    val dialogType = "dialogs/SimpleDialog"

    @SlingObject
    private lateinit var resource: Resource

    @PostConstruct
    fun init() {
        dialog = imageDialog(resource)
    }

}