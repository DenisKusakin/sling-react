package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue
import com.cathcart93.sling.components.models.spectacle.impl.builder.*

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@ReactController(Constants.LINK)
class LinkModel : IReactController, BaseModel(), ReactModel {

    @ValueMapValue
    var text: String? = null

    @ValueMapValue
    var href: String? = null

    @ValueMapValue
    var target: String? = null

    @SlingObject
    private lateinit var resource: Resource

    override fun toReact(isEditMode: Boolean): SpectacleTag {
        val component = link(if (href == null) "" else href!!, if (text == null) "" else text!!) {
            target = this@LinkModel.target?.toLinkTarget()
            italic = this@LinkModel.italic
            bold = this@LinkModel.bold
            caps = this@LinkModel.caps
            margin = this@LinkModel.margin
            padding = this@LinkModel.padding
        }
        return if (!isEditMode)
            component
        else
            component.edit {
                deleteUrl = resource.path
                editUrl = resource.path
                text(name = "text", title = "Link Text", value = if (text == null) "" else text!!)
                text(name = "href", title = "Href", value = if (href == null) "" else href!!)
                select(
                        name = "target",
                        title = "Target",
                        value = if (target == null) "" else target!!,
                        options = listOf(
                                SelectOption(label = "Blank", value = "_blank"),
                                SelectOption(label = "Self", value = "_self"),
                                SelectOption(label = "Parent", value = "_parent"),
                                SelectOption(label = "Top", value = "_top")
                        )
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
            }
    }

    private fun String.toLinkTarget(): LinkTarget {
        return when (this) {
            "_blank" -> Blank
            "_self" -> Self
            "_parent" -> Parent
            "_top" -> Top
            else -> Self
        }
    }
}