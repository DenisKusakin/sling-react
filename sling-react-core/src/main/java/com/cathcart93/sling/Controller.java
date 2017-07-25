package com.cathcart93.sling;

import com.google.gson.annotations.Expose;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;

import java.util.Map;


/**
 * Created by Kusak on 7/15/2017.
 */
public interface Controller {
//    default Object getProps() {
//        return this;
//    }

    default void init() {
    }
}
