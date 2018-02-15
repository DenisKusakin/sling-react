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
@ReactController("BlockQuote")
class BlockQuote : IReactController, SlideComponent {
    @ReactProp
    private var children = ArrayList<Any>()

    @ValueMapValue
    private lateinit var quote: String

    @ValueMapValue
    private lateinit var cite: String

    @ReactProp("__dialog")
    private lateinit var dialog: SimpleDialog

    @ReactProp("__dialog_type")
    private val dialogType = "dialogs/SimpleDialog"

    @SlingObject
    private lateinit var resource: Resource

    @PostConstruct
    fun init() {
        children = ArrayList()
        children.add(Quote(quote))
        children.add(Cite(cite))

        dialog = dialog(resource) {
            text(name = "quote", title = "Quote")
            text(name = "cite", title = "Cite")
        }
    }

    @ReactController("Quote")
    data class Quote(@ReactProp val children: String) : IReactController

    @ReactController("Cite")
    data class Cite(@ReactProp val children: String) : IReactController

}