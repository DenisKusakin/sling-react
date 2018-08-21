package com.cathcart93.sling.components.models.aempoc

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toJson
import com.cathcart93.sling.components.services.ReactSsrService
import com.cathcart93.sling.components.services.ReactSsrServiceImpl
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.OSGiService
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import javax.annotation.PostConstruct
import javax.inject.Inject

@Model(
        adaptables = [Resource::class, SlingHttpServletRequest::class]
)
class AemReactPageModel {
    @SlingObject
    private lateinit var resource: Resource

    @OSGiService
    private lateinit var reactSsrService: ReactSsrService

    private val jsFilePath = "/etc/aem-poc-clientlibs/aem-poc.server.js"

    private var reactRoot: ReactElement? = null

    @PostConstruct
    fun init() {
        reactRoot = resource.getChild("content")?.adaptTo(ParsysModel::class.java)?.toReact()
    }

    fun getInitialStateJson(): String {
        return reactRoot!!.toJson()
    }

    fun getHtml(): String {
        return reactSsrService.renderToHtmlString(resource.resourceResolver, jsFilePath, reactRoot!!.toJson())
    }
}