package com.cathcart93.sling;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

/**
 * Created by Kusak on 7/16/2017.
 */
public class ComponentHelper {
    public static String getComponentName(SlingHttpServletRequest request){
        return getComponentName(request.getResource());
    }

    public static String getComponentName(Resource resource){
        ValueMap valueMap = resource.adaptTo(ValueMap.class);
        return valueMap.get("component", String.class);
    }
}
