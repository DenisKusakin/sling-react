package com.cathcart93.sling.components.models

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.ChildResource

/**
 * Created by Kusak on 7/15/2017.
 */
@Model(adaptables = arrayOf(Resource::class))
@ReactController(componentName = "ImageGallery")
class ImageGalleryController : IReactController {

    @ChildResource
    @ReactProp
    private val items: List<ImageGalleryItem>? = null

}
