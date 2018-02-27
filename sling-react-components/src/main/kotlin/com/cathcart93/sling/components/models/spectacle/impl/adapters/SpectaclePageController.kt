package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.PageController
import com.cathcart93.sling.components.models.spectacle.api.Deck
import com.cathcart93.sling.components.models.spectacle.api.Slide
import com.cathcart93.sling.components.models.spectacle.templates.spectacleTemplate
import com.cathcart93.sling.components.models.spectacle.templates.spectacleTemplate2
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactProp
import com.cathcart93.sling.core.services.BeanSerializer
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.OSGiService
import org.apache.sling.models.annotations.injectorspecific.SlingObject
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
    @Transient
    private lateinit var resource: Resource

    @SlingObject
    @Transient
    private lateinit var request: SlingHttpServletRequest

    @OSGiService
    @Transient
    private lateinit var beanSerializer: BeanSerializer

    private lateinit var props: String

    @PostConstruct
    fun init() {
        val container = resource.adaptTo(DeckModel::class.java)
        //        val container = if (textFromRequest == null)
//            spectacleTemplate()
//        val container = spectacleTemplate(resource)

        val isEditMode = request.getParameter("isEdit") != null
        props = beanSerializer.convertToMap(App("${resource.path}.json?isEdit=$isEditMode", isEditMode, container))
    }

    fun getData(): String {
        return props
    }

    override fun getProps(): String {
        return props
    }

    class App(url: String, isEditMode: Boolean, content: Any?) {

        private val isEditMode = isEditMode

        private val content = content

        private val url = url
    }
}