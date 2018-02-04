package com.cathcart93.sling.components.models.spectacle

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

    @ReactProp("__dialog")
    private lateinit var dialog: SimpleDialog

    @ReactProp("__dialog_type")
    private val dialogType = "dialogs/SimpleDialog"

    @SlingObject
    private lateinit var resource: Resource

    @PostConstruct
    fun init() {
        val propsMap = HashMap<String, String>()
        propsMap.put("text", "Text")
        propsMap.put("textColor", "Text Color")
        dialog = SimpleDialogImpl(resource, propsMap)
    }

}