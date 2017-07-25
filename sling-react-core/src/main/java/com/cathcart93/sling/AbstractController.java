package com.cathcart93.sling;

import org.apache.sling.api.SlingHttpServletRequest;

public class AbstractController implements Controller {
    protected SlingHttpServletRequest request;

    public void setRequest(SlingHttpServletRequest request) {
        this.request = request;
    }
}
