package com.cathcart93.sling.components.models.aempoc

import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = [ImageGalleryItemModel::class]
)
class ImageGalleryItemModel {
    @ValueMapValue
    var original: String = ""

    @ValueMapValue
    var thumbnail: String = ""

}