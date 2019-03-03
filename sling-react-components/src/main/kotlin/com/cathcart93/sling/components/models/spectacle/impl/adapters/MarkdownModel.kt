package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.ResourceTypesConstants
import com.cathcart93.sling.components.models.spectacle.impl.builder.edit
import com.cathcart93.sling.components.models.spectacle.impl.builder.markdown
import com.cathcart93.sling.components.models.spectacle.impl.builder.multilineText
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = [MarkdownModel::class, ReactModel::class],
        resourceType = [ResourceTypesConstants.MARKDOWN]
)
class MarkdownModel : ReactModel {
    @ValueMapValue
    //Empty string is not working with Markdown component
    var source: String = " "

    @SlingObject
    private lateinit var resource: Resource

    override fun render(context: RenderContext): ReactElement {
        val isEditMode = context.isEditMode
        val component = markdown(source)
        return (if (!isEditMode)
            component
        else
            component.edit(resource) {
                multilineText(name = "source", title = "Source", value = source)
            }).render()

    }
}