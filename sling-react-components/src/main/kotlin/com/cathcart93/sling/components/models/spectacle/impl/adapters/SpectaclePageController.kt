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

    @PostConstruct
    fun init() {
        val container = resource.adaptTo(DeckModel::class.java)?.toReact(true)
//                val container = if (textFromRequest == null)
//            spectacleTemplate()
//        val container = spectacleTemplate(resource)
//        val container = spectacleTemplate("Spectacle Template")
//        val isEditMode = request.getParameter("isEdit") != null
//        props = beanSerializer.convertToMap(App("${resource.path}.json?isEdit=$isEditMode", isEditMode, container?.toReactElement()?.toMap()))
        val rootReactElement = ReactElement("SpectacleAuthorRoot", mapOf(
                "url" to "${resource.path}.json".toReactProp(),
                "content" to container!!.toReactElement()
        ))
        props = rootReactElement.toJson()
    }

    fun getData(): String {
        return props
    }

    override fun getProps(): String {
        return props
    }
}