package com.cathcart93.sling.components.models

import com.cathcart93.sling.components.NextJSEndpoint
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.Self

@Model(adaptables = [Resource::class], adapters = [NextJSEndpoint::class])
class DefaultNextJSEndpointResolver : NextJSEndpoint {

    @Self
    private lateinit var resource: Resource

    private val nextJsServer = "http://localhost:3000"

    override fun renderUrl(): String {
        val resourceType = resource.resourceType
        val jsonUrl = "http://localhost:8080${resource.path}.json"
        val assetPrefix = "${resource.path}/_next.html"
        return nextJsServer + (if (resourceType.startsWith("/")) resourceType else "/$resourceType") +
                "?dataUrl=$jsonUrl&assetPrefix=$assetPrefix"
    }

    override fun baseUrl(): String {
        return nextJsServer
    }
}