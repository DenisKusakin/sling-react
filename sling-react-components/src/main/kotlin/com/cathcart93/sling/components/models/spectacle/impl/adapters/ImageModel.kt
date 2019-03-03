package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = [ImageModel::class, ReactModel::class],
        resourceType = [ResourceTypesConstants.IMAGE]
)
class ImageModel : BaseModel(), ReactModel {

    @ValueMapValue
    var src: String = ""

    @ValueMapValue
    var height: String? = null

    @ValueMapValue
    var width: String? = null

    @SlingObject
    private lateinit var resource: Resource

    override fun render(context: RenderContext): ReactElement {
        val isEditMode = context.isEditMode
        val component = image(src) {
            height = this@ImageModel.height
            width = this@ImageModel.width
        }.appear(shouldAppear)
        return (if (!isEditMode)
            component
        else
            component.edit(resource) {
                text(name = "src", title = "src", value = src)
                text(name = "height", title = "Height", value = if (height == null) "" else height!!)
                text(name = "width", title = "Width", value = if (width == null) "" else width!!)
                checkbox(name = "shouldAppear", title = "Should Appear", value = shouldAppear)
            }).render()
    }

}