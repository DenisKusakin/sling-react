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
@ReactController(Constants.CODE_PANE)
class CodeModel : IReactController, BaseModel(), ReactModel {

    @ValueMapValue
    var source: String? = null

    @ValueMapValue
    var lang: String = "javascript"

    @SlingObject
    private lateinit var resource: Resource

    override fun toReact(isEditMode: Boolean): SpectacleTag {
        val component = code(source = if (source == null) "" else source!!, lang = lang){
            italic = this@CodeModel.italic
            bold = this@CodeModel.bold
            caps = this@CodeModel.caps
        }
        return if (!isEditMode)
            component
        else
            component.edit {
                deleteUrl = resource.path
                editUrl = resource.path
                multilineText(name = "source", title = "Source", value = if (source == null) "" else source!!)
                select(name = "lang", title = "Language", value = lang, options = listOf(
                        SelectOption(label = "JavaScript", value = "javascript")
                ))
            }
    }

}