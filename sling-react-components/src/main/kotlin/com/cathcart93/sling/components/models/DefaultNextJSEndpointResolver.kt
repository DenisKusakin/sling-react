package com.cathcart93.sling.components.models

import com.cathcart93.sling.components.NextJSEndpoint
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.Self
import javax.annotation.PostConstruct

@Model(adaptables = [Resource::class], adapters = [NextJSEndpoint::class, DefaultNextJSEndpointResolver::class])
class DefaultNextJSEndpointResolver : NextJSEndpoint {

    @Self
    private lateinit var resource: Resource

    private val confRoot = "/_next"

    private var nextJsServer: String? = null

    private var assetPrefix: String? = null

    private var relativeNextJsPath: String = ""

    private var slingUrl: String = ""

    @PostConstruct
    fun init() {
        val resourceResolver = resource.resourceResolver
        val resourceTypeTokens = resource.resourceType.split("/").filter { !it.isEmpty() }
        var currentRootResource = resourceResolver.getResource(confRoot)!!
        slingUrl = currentRootResource.valueMap.get("slingUrl", String::class.java)!!

        resourceTypeTokens.forEach {
            val child = currentRootResource.getChild(it)
            if (child != null) {
                currentRootResource = child
            }
        }
        val relativeConfPath = currentRootResource.path.substringAfter("$confRoot/")
        relativeNextJsPath = resource.resourceType.substringAfter(relativeConfPath)
        nextJsServer = currentRootResource.valueMap.get("url", String::class.java)!!
        assetPrefix = "${currentRootResource.path}.html"
    }

    override fun renderUrl(): String {
        val jsonUrl = "$slingUrl${resource.path}.json"

        return nextJsServer + (if (relativeNextJsPath.startsWith("/"))
            relativeNextJsPath else "/$relativeNextJsPath") +
                "?dataUrl=$jsonUrl&assetPrefix=$assetPrefix"
    }

    override fun baseUrl(): String {
        return nextJsServer!!
    }
}