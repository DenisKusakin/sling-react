package com.cathcart93.sling.components.models.aempoc

import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = ["aem-poc/components/image-gallery"],
        adapters = [ImageGalleryModel::class, AEMReactModel::class]
)
class ImageGalleryModel : AEMReactModel {
    @SlingObject
    private lateinit var resource: Resource

    override fun toReact(isEditMode: Boolean): ReactElement {
        val items = resource.getChild("slides")?.children
                ?.mapNotNull { it.adaptTo(ImageGalleryItemModel::class.java) }
                ?.map { ImageGalleryItem(original = it.original, thumbnail = it.thumbnail) } ?: emptyList()
        return ImageGallery(items).toReactElement()
    }

}