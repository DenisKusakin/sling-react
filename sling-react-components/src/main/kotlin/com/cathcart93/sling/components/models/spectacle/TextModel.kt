package com.cathcart93.sling.components.models.spectacle

import com.cathcart93.sling.components.models.spectacle.api.SimpleDialog
import com.cathcart93.sling.components.models.spectacle.api.SlideComponent
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
@ReactController("Text")
class TextModel : IReactController, SlideComponent {
    @ReactProp("children")
    @ValueMapValue
    private lateinit var text: String

    @ReactProp
    @ValueMapValue
    private var fit: Boolean = true

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
        dialog = dialog(resource) {
            text(name = "text", title = "Text")
            checkbox(name = "fit", title = "Fit")
            select(name = "textColor", title = "Color") {
                option(label = "Red", value = "red")
                option(label = "Green", value = "green")
                option(label = "Blue", value = "blue")
                option(label = "Black", value = "black")
            }
            select(name = "lineHeight", title = "Line Height") {
                option("1", "1")
                option("2", "2")
                option("3", "3")
                option("4", "4")
            }
        }
    }

}