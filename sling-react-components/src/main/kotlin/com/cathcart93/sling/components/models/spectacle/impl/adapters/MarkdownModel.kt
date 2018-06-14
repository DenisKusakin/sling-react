package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.components.models.spectacle.impl.builder.SpectacleTag
import com.cathcart93.sling.components.models.spectacle.impl.builder.edit
import com.cathcart93.sling.components.models.spectacle.impl.builder.markdown
import com.cathcart93.sling.components.models.spectacle.impl.builder.multilineText
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = [MarkdownModel::class, ReactModel::class],
        resourceType = [Constants.MARKDOWN]
)
class MarkdownModel : ReactModel {
    @ValueMapValue
    var source: String = ""

    @SlingObject
    private lateinit var resource: Resource

    override fun toReact(isEditMode: Boolean): SpectacleTag {
        val component = markdown(source)
        return if (!isEditMode)
            component
        else
            component.edit {
                editUrl = resource.path
                deleteUrl = resource.path
                multilineText(name = "source", title = "Source", value = source)
            }

    }
}