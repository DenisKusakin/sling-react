package com.cathcart93.sling;

import com.cathcart93.sling.services.BeanSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Map;

/**
 * Created by Kusak on 7/15/2017.
 */
public abstract class BaseController implements Controller {
    @SlingObject
    protected Resource resource;

}
