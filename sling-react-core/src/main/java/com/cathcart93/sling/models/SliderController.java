package com.cathcart93.sling.models;

import com.cathcart93.sling.BaseController;
import com.google.gson.annotations.Expose;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Created by Kusak on 7/15/2017.
 */
@Model(adaptables = Resource.class)
public class SliderController extends BaseController {
    @Expose
    private String __type = "Slider";

    @Expose
    @ValueMapValue(name = "title", optional = true)
    private String title;

}
