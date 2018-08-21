package com.cathcart93.sling.components.models.aempoc

import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.Heading
import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.Parsys
import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.ReactTag
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = [ParsysModel::class]
)
class ParsysModel : AEMReactModel {
    @SlingObject
    private lateinit var resource: Resource

    override fun toReact(): ReactElement {
        return Parsys(resource.children.mapNotNull { it.adaptTo(AEMReactModel::class.java)?.toReact() })
                .toReactElement()
    }

}