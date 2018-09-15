package com.cathcart93.sling.components.models.aempoc

import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.Heading
import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.ReactTag
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = ["aem-poc/components/heading"],
        adapters = [HeadingModel::class, AEMReactModel::class]
)
class HeadingModel : AEMReactModel {
    @ValueMapValue
    var text: String = ""

    override fun toReact(isEditMode: Boolean): ReactElement {
        return Heading(text).toReactElement()
    }

}