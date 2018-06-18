package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.ResourceTypesConstants
import com.cathcart93.sling.components.models.spectacle.impl.builder.*
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = [TextModel::class, ReactModel::class],
        resourceType = [ResourceTypesConstants.TEXT]
)
class TextModel :  BaseModel(), ReactModel {
    @ValueMapValue
    private var text: String? = null

    @ValueMapValue
    private var fit: Boolean? = null

    @ValueMapValue
    private var lineHeight: Int? = null

    @SlingObject
    private lateinit var resource: Resource

    override fun toReact(isEditMode: Boolean): SpectacleTag {
        val component = text(if (text == null) "" else text!!) {
            lineHeight = this@TextModel.lineHeight
            fit = this@TextModel.fit
            italic = this@TextModel.italic
            bold = this@TextModel.bold
            caps = this@TextModel.caps
            margin = this@TextModel.margin
            padding = this@TextModel.padding
            if (this@TextModel.textColor != null) {
                textColor = HexColor(this@TextModel.textColor!!)
            }
        }.appear(shouldAppear)
        return if (isEditMode)
            component.edit(resource) {
                text(name = "text", title = "Text", value = if (text == null) "" else text!!)
                checkbox(name = "fit", title = "Fit", value = if (fit == null) false else fit!!)
                checkbox(name = "italic", value = if (italic != null) italic!! else false, title = "Italic")
                checkbox(name = "bold", value = if (bold != null) bold!! else false, title = "Bold")
                checkbox(name = "caps", value = if (caps != null) caps!! else false, title = "Caps")
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
                checkbox(name = "shouldAppear", title = "Should Appear", value = shouldAppear)
            }
        else component
    }
}