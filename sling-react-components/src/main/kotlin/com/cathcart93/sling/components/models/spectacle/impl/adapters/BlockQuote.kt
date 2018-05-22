package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.BlockQuote
import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.components.models.spectacle.api.SimpleDialog
import com.cathcart93.sling.components.models.spectacle.dialogs.baseDialog
import com.cathcart93.sling.components.models.spectacle.dialogs.simpleDialog
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
@ReactController(Constants.BLOCK_QUOTE)
class BlockQuote : IReactController, BlockQuote, BaseModel() {
    @ReactProp
    override val children = ArrayList<BlockQuote.BlockQuoteChildComponent>()

    @ValueMapValue
    private lateinit var quote: String

    @ValueMapValue
    private lateinit var cite: String

    @ReactProp("__dialog")
    lateinit var dialog: SimpleDialog

    @ReactProp("__dialog_type")
    val dialogType = "dialogs/SimpleDialog"

    @SlingObject
    private lateinit var resource: Resource

    @PostConstruct
    fun init() {
        children.add(Quote(quote))
        children.add(Cite(cite))

        dialog = simpleDialog(resource) {
            text(name = "quote", title = "Quote")
            text(name = "cite", title = "Cite")
            dialog(baseDialog(resource))
        }
    }

    @ReactController("Quote")
    data class Quote(@ReactProp override val children: String) : BlockQuote.Quote

    @ReactController("Cite")
    data class Cite(@ReactProp override val children: String) : BlockQuote.Cite

}