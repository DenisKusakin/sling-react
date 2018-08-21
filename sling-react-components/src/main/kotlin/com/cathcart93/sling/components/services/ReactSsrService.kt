package com.cathcart93.sling.components.services

import org.apache.sling.api.resource.ResourceResolver

/**
 * @author Denis_Kusakin. 8/21/2018.
 */
interface ReactSsrService {
    fun renderToHtmlString(resourceResolver: ResourceResolver, pathToJsFile: String, reactElementJson: String): String
}