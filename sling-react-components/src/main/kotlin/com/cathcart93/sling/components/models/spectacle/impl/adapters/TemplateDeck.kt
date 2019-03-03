package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.ResourceTypesConstants
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import spectacleTemplate

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = [TemplateDeck::class, ReactModel::class],
        resourceType = [ResourceTypesConstants.TEMPLATE_DECK]
)
class TemplateDeck : ReactModel {
    override fun render(context: RenderContext): ReactElement {
        return spectacleTemplate("Kotlin-Based ")
    }
}