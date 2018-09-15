package com.cathcart93.sling.components.models.aempoc

import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.AuthorWrapperV2
import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.HeadingWithButton
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.Self
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue
import javax.annotation.PostConstruct

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = ["aem-poc/components/heading-with-button"],
        adapters = [HeadingWithButtonModel::class, AEMReactModel::class]
)
class HeadingWithButtonModel : AEMReactModel {
    @ValueMapValue
    var text: String = ""

    @Self
    var resource: Resource? = null

    var button: ReactElement? = null

    @PostConstruct
    private fun init() {
        button = resource?.getChild("button")?.adaptTo(HeadingButton::class.java)?.toReact()
    }

    override fun toReact(isEditMode: Boolean): ReactElement {
        if (isEditMode) {
            return HeadingWithButton(text, AuthorWrapperV2(resource!!.path + "/button").toReactElement()).toReactElement()
        }
        return HeadingWithButton(text, if (button == null) false.toReactProp() else button!!).toReactElement()
    }

}