package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.components.models.spectacle.api.SimpleDialog
import com.cathcart93.sling.components.models.spectacle.api.Text
import com.cathcart93.sling.components.models.spectacle.dialogs.textDialog
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import com.google.gson.annotations.SerializedName
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue
import javax.annotation.PostConstruct

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@ReactController(Constants.TEXT)
class TextModel : IReactController, Text {
    @SerializedName("__type")
    val type = Constants.TEXT

    @ValueMapValue(name = "text")
    override lateinit var children: String

    @ValueMapValue
    override var fit: Boolean = true

    @ValueMapValue
    override var textColor: String? = null

    @ValueMapValue
    override var lineHeight: Int = 1

    @SerializedName("__dialog")
    private lateinit var dialog: SimpleDialog

    @SerializedName("__dialog_type")
    private val dialogType = "dialogs/SimpleDialog"

    @SlingObject
    private lateinit var resource: Resource

    @PostConstruct
    fun init() {
        dialog = textDialog(resource)
    }

}