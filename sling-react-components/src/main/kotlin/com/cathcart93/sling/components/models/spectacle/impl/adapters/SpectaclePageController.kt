package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.PageController
import com.cathcart93.sling.components.v2.*
import com.google.gson.Gson
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import javax.annotation.PostConstruct

/**
 * Created by Kusak on 7/15/2017.
 */
@Model(
        adaptables = [Resource::class, SlingHttpServletRequest::class],
        adapters = [PageController::class],
        resourceType = ["sling-spectacle/client-page"]
)
class SpectaclePageController : PageController {

    @SlingObject
    private lateinit var resource: Resource

    @SlingObject
    private lateinit var request: SlingHttpServletRequest

    private lateinit var props: String

    private val authorJsUrl = "/etc/sling-spectacle/spectacle.client.author.js"

    private val previewJsUrl = "/etc/sling-spectacle/spectacle.client.js"

    private var isPreviewMode = false

    @PostConstruct
    fun init() {
        val renderer = SimpleRecursiveRenderer()
        val element = (resource.valueMap.asString("xml"))?.let {
            MeduzaSpectacle { it }
        } ?: ResourceDeck {
            resource
        }
        val gson = Gson()

        props = gson.toJson(renderer.render(element))
    }

    fun getData(): String {
        return props
    }

    fun getJsUrl(): String {
        return if (isPreviewMode) previewJsUrl else authorJsUrl
    }

    override fun getProps(): String {
        return props
    }
}