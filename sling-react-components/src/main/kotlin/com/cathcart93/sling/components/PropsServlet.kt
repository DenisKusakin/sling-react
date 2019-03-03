package com.cathcart93.sling.components

import com.cathcart93.sling.components.models.spectacle.impl.adapters.ReactModel
import com.cathcart93.sling.components.models.spectacle.impl.adapters.RenderContextImpl
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toJson
import org.apache.felix.scr.annotations.Properties
import org.apache.felix.scr.annotations.Property
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.slf4j.LoggerFactory

@SlingServlet(resourceTypes = ["sling-react/page", "sling-react/client-page", "spectacle/meduza-spectacle", "sling-spectacle/client-page"], extensions = ["json"])
@Properties(Property(
        name = "service.description",
        value = ["Props Servlet"]),
        Property(name = "service.vendor", value = ["Cathcart 93"]))
class PropsServlet : SlingSafeMethodsServlet() {

    private val log = LoggerFactory.getLogger(PropsServlet::class.java)

    override fun doGet(request: SlingHttpServletRequest,
                       response: SlingHttpServletResponse) {
        val writer = response.writer
        val controller = request.resource.adaptTo(ReactModel::class.java)

        val renderContext = RenderContextImpl(isEditMode = true)
        writer.append(controller!!.render(renderContext).toJson())
        response.contentType = "json"
    }

}

