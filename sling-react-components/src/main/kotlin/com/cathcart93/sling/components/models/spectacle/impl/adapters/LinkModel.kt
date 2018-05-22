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
import com.cathcart93.sling.components.models.spectacle.api.Link
import com.cathcart93.sling.components.models.spectacle.dialogs.headingDialog
import com.cathcart93.sling.components.models.spectacle.dialogs.linkDialog

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@ReactController(Constants.LINK)
class LinkModel : IReactController, Link, BaseModel() {

    @ReactProp
    @ValueMapValue(name = "text")
    override var children: String? = ""

    @ReactProp
    @ValueMapValue
    override var href: String? = ""

    @ReactProp
    @ValueMapValue
    override var target: String? = "self"

    @ReactProp("__dialog")
    lateinit var dialog: SimpleDialog

    @ReactProp("__dialog_type")
    val dialogType = "dialogs/SimpleDialog"

    @SlingObject
    private lateinit var resource: Resource

    @PostConstruct
    fun init() {
        dialog = linkDialog(resource)
    }

}