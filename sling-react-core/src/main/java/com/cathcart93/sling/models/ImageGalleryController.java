package com.cathcart93.sling.models;

import com.cathcart93.sling.BaseController;
import com.google.gson.annotations.Expose;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.spi.injectorspecific.InjectAnnotation;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Kusak on 7/15/2017.
 */
@Model(adaptables = Resource.class)
public class ImageGalleryController extends BaseController {
    @Expose
    private String __type = "ImageGallery";

    private static final String ITEM_NODE_NAME = "items";

    @Expose
    @ChildResource
    private List<ImageGalleryItem> items;

}
