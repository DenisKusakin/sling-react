package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.components.models.spectacle.impl.builder.*
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@ReactController(Constants.BLOCK_QUOTE)
class BlockQuote : IReactController, BaseModel(), ReactModel {
    @ValueMapValue
    private var quote: String? = null

    @ValueMapValue
    private var cite: String? = null

    @SlingObject
    private lateinit var resource: Resource

    override fun toReact(isEditMode: Boolean): SpectacleTag {
        val component = blockQuote {
            italic = this@BlockQuote.italic
            bold = this@BlockQuote.bold
            caps = this@BlockQuote.caps
            if (this@BlockQuote.textColor != null) {
                textColor = HexColor(this@BlockQuote.textColor!!)
            }
            if (this@BlockQuote.quote != null) {
                quote(this@BlockQuote.quote!!) {

                }
            }
            if (this@BlockQuote.cite != null) {
                cite(this@BlockQuote.cite!!) {

                }
            }
        }
        return if(!isEditMode)
            component
        else
            component.edit {
                editUrl = resource.path
                deleteUrl = resource.path
                text(name = "quote", title = "Quote", value = if(quote == null) "" else quote!!)
                text(name = "cite", title = "Cite", value = if(cite == null) "" else cite!!)
                select(
                        name = "textColor",
                        title = "Text Color",
                        value = if (textColor == null) "" else textColor!!,
                        options = listOf(
                                SelectOption(label = "Primary", value = "primary"),
                                SelectOption(label = "Secondary", value = "secondary"),
                                SelectOption(label = "Tertiary", value = "tertiary"),
                                SelectOption(label = "Quarternary", value = "quarternary"),
                                SelectOption(label = "Default", value = "")
                        )
                )
            }
    }

}