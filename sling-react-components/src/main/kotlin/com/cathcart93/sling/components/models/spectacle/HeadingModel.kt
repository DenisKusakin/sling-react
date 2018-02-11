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
        dialog = dialog(resource) {
            text("text", "Text")
            checkbox("caps", "Caps")
            checkbox("fir", "Fit")
            select("textColor", "Color") {
                option("Red", "red")
                option("Green", "green")
                option("Blue", "blue")
                option("Black", "black")
            }
            select("size", "Size") {
                option("H1", "1")
                option("H2", "2")
                option("H3", "3")
                option("H4", "4")
                option("H5", "5")
            }
            select("height", "Height") {
                option("1", "1")
                option("2", "2")
                option("3", "3")
                option("4", "4")
            }
        }
    }

}