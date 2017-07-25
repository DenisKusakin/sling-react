package com.cathcart93.sling.models;

import com.google.gson.annotations.Expose;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class ImageGalleryItem {
    @Expose
    @ValueMapValue(name = "original", optional = true)
    private String original;

    @Expose
    @ValueMapValue(name = "thumbnail", optional = true)
    private String thumbnail;
}
