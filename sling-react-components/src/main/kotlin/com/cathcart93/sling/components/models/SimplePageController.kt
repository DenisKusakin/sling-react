package com.cathcart93.sling.components.models

import com.cathcart93.sling.components.Container
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import com.cathcart93.sling.core.services.BeanSerializer
import com.cathcart93.sling.core.services.ReactEngine
import org.apache.felix.scr.annotations.Reference
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
        resourceType = ["sling-react/page"]
)
@ReactController(componentName = "Page")
class SimplePageController : IReactController, PageController {
    private val SOURCE_PATH = "/etc/react-clientlibs/server.js"

    @SlingObject
    private lateinit var resource: Resource

    @SlingObject
    private lateinit var request: SlingHttpServletRequest

    @OSGiService
    private lateinit var beanSerializer: BeanSerializer

    @OSGiService
    private lateinit var react: ReactEngine

    private lateinit var props: String

    @PostConstruct
    fun init() {
        val components = resource.children.mapNotNull { it.adaptTo(IReactController::class.java) }
        val componentsMeta = resource.children.map { Container.ComponentMeta(it.path) }
        val container = Container(components = components, path = resource.path, componentsMeta = componentsMeta)
        val isEditMode = request.getParameter("isEdit") != null
        props = beanSerializer.convertToMap(App("${resource.path}.json?isEdit=$isEditMode", isEditMode, container))
    }

    fun getHtml(): String {
        return react.render(props, SOURCE_PATH)
    }

    override fun getProps(): String {
        return props
    }

    class App(url: String, isEditMode: Boolean, content: Any) {

        @ReactProp
        private val isEditMode = isEditMode

        @ReactProp
        private val content = content

        @ReactProp
        private val url = url
    }
}