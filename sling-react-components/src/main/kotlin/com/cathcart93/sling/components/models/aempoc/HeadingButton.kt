package com.cathcart93.sling.components.models.aempoc

import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.AuthorWrapperV2
import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.HeadingWithButtonButton
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toJson
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.Self
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = ["aem-poc/components/button"],
        adapters = [HeadingButton::class, AEMReactModel::class]
)
class HeadingButton : AEMReactModel {
    @ValueMapValue
    var title: String = ""

    @Self
    var resource: Resource? = null

    override fun toReact(isEditMode: Boolean): ReactElement {
//        return (if (!isEditMode) HeadingWithButtonButton(title) else AuthorWrapperV2(resource!!.path)).toReactElement()
        return HeadingWithButtonButton(title).toReactElement()
    }

    fun toPreviewJson(): String {
        return HeadingWithButtonButton(title).toReactElement().toJson();
    }

}