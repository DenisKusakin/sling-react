package com.cathcart93.sling.components.models

import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(adaptables = arrayOf(Resource::class))
class ImageGalleryItem {
    @ValueMapValue(name = "original", optional = true)
    @ReactProp
    private val original: String? = null

    @ValueMapValue(name = "thumbnail", optional = true)
    @ReactProp
    private val thumbnail: String? = null
}
