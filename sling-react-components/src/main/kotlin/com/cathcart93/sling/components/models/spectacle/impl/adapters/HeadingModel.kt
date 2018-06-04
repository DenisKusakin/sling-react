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
@ReactController(Constants.HEADING)
class HeadingModel : IReactController, BaseModel(), ReactModel {

    @ValueMapValue
    var text: String = ""

    @ValueMapValue
    var size: Int = 6

    @ValueMapValue
    var fit: Boolean = true

    @ValueMapValue
    var lineHeight: Int = 1

    @SlingObject
    private lateinit var resource: Resource

    override fun toReact(isEditMode: Boolean): SpectacleTag {
        val component = heading(text) {
            size = this@HeadingModel.size
            fit = this@HeadingModel.fit
            lineHeight = this@HeadingModel.lineHeight
            italic = this@HeadingModel.italic
            bold = this@HeadingModel.bold
            caps = this@HeadingModel.caps
            margin = this@HeadingModel.margin
            padding = this@HeadingModel.padding
            if (this@HeadingModel.textColor != null) {
                textColor = HexColor(this@HeadingModel.textColor!!)
            }
        }
        return if (isEditMode) component.edit {
            editUrl = resource.path
            deleteUrl = resource.path
            text(name = "text", title = "Text", value = text)
            select(
                    name = "size",
                    title = "Size",
                    value = size.toString(),
                    options = (1..5).map { SelectOption(label = "H$it", value = it.toString()) }
            )
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
        } else component
    }

}