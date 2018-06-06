package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.PageController
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toJson
//import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toMap
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toReactProp
//import com.cathcart93.sling.components.models.spectacle.templates.spectacleTemplate
//import com.cathcart93.sling.components.models.spectacle.templates.spectacleTemplate2
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactProp
import com.cathcart93.sling.core.services.BeanSerializer
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.OSGiService
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import spectacleTemplate
import javax.annotation.PostConstruct

/**
 * Created by Kusak on 7/15/2017.
 */
@Model(
        adaptables = [Resource::class, SlingHttpServletRequest::class],
        adapters = [PageController::class],
        resourceType = ["sling-react/client-page"]
)
class SpectaclePageController : IReactController, PageController {

    @SlingObject
    private lateinit var resource: Resource

    @SlingObject
    private lateinit var request: SlingHttpServletRequest

    @OSGiService
    private lateinit var beanSerializer: BeanSerializer

    private lateinit var props: String

    private val authorJsUrl = "/etc/react-clientlibs/spectacle.client.author.js"

    private val previewJsUrl = "/etc/react-clientlibs/spectacle.client.js"

    private var isPreviewMode = false

    @PostConstruct
    fun init() {
        isPreviewMode = request.getParameter("preview") != null
        val container = resource.adaptTo(DeckModel::class.java)?.toReact(!isPreviewMode)
//                val container = if (textFromRequest == null)
//            spectacleTemplate()
//        val container = spectacleTemplate(resource)
//        val container = spectacleTemplate("Spectacle Template")
//        props = beanSerializer.convertToMap(App("${resource.path}.json?isEdit=$isEditMode", isEditMode, container?.toReactElement()?.toMap()))
        val rootReactElement = if (!isPreviewMode) ReactElement("SpectacleAuthorRoot", mapOf(
                "url" to "${resource.path}.json".toReactProp(),
                "content" to container!!.toReactElement()
        )) else container!!.toReactElement()
        props = rootReactElement.toJson()
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