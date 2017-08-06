package com.cathcart93.sling.components

import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource

fun SlingHttpServletRequest.getComponentName(): String? {
    return this.resource.getComponentName()
}

fun Resource.getComponentName(): String? {
    val valueMap = this.valueMap
    return valueMap.get("component", String::class.java)
}