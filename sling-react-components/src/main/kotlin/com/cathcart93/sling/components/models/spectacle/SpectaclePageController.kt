package com.cathcart93.sling.components.models.spectacle

import com.cathcart93.sling.components.Container
import com.cathcart93.sling.components.models.PageController
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
    private lateinit var resource: Resource

    @SlingObject
    private lateinit var request: SlingHttpServletRequest

    @OSGiService
    private lateinit var beanSerializer: BeanSerializer

    private lateinit var props: String

    @PostConstruct
    fun init() {
//        val components = resource.children.mapNotNull { it.adaptTo(IReactController::class.java) }
//        val componentsMeta = resource.children.map { Container.ComponentMeta(it.path) }
//        val container = Container(components = components, path = resource.path, componentsMeta = componentsMeta)
        val container = resource.adaptTo(DeckModel::class.java)
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

        @ReactProp
        private val isEditMode = isEditMode

        @ReactProp
        private val content = content

        @ReactProp
        private val url = url
    }
}