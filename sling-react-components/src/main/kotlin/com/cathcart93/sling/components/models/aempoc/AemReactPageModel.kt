package com.cathcart93.sling.components.models.aempoc

import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model

@Model(
        adaptables = [Resource::class, SlingHttpServletRequest::class]
)
class AemReactPageModel {
}