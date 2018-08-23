package com.cathcart93.sling.components.models.aempoc

import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.AuthorWrapper
import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.Page
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toJson
import com.cathcart93.sling.components.services.ReactSsrService
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.OSGiService
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import javax.annotation.PostConstruct

@Model(
        adaptables = [Resource::class, SlingHttpServletRequest::class],
        adapters = [AemReactPageModel::class, AEMReactModel::class],
        resourceType = ["aem-poc/aem-poc-page"]
)
class AemReactPageModel : AEMReactModel {
    @SlingObject
    private lateinit var request: SlingHttpServletRequest

    private lateinit var resource: Resource

    @OSGiService
    private lateinit var reactSsrService: ReactSsrService

    private val jsFilePath = "/etc/aem-poc-clientlibs/aem-poc.server.js"

    private var reactRoot: ReactElement? = null

    @PostConstruct
    fun init() {
        resource = request.resource
        val navigationReactElement = resource.adaptTo(NavigationModel::class.java)!!.toReact()
        val isWcmModeDisabled = request.getParameter("wcmmode") == "disabled"
        val content = resource.getChild("content")?.adaptTo(ParsysModel::class.java)?.toReact()
        reactRoot = Page(
                navigationReactElement,
                if (isWcmModeDisabled) content!! else AuthorWrapper("${resource.path}/content.html").toReactElement()
        ).toReactElement()
    }

    fun getInitialStateJson(): String {
        return reactRoot!!.toJson()
    }

    fun getHtml(): String {
        return reactSsrService.renderToHtmlString(resource.resourceResolver, jsFilePath, reactRoot!!.toJson())
    }

    override fun toReact(): ReactElement {
        return reactRoot!!
    }
}