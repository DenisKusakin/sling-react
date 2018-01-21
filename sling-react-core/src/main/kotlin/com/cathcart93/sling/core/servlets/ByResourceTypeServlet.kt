package com.cathcart93.sling.core.servlets

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.services.BeanSerializer
import com.cathcart93.sling.core.services.ReactEngine
import java.io.IOException

import javax.servlet.ServletException

import org.apache.felix.scr.annotations.Properties
import org.apache.felix.scr.annotations.Property
import org.apache.felix.scr.annotations.Reference
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.slf4j.LoggerFactory

@SlingServlet(resourceTypes = arrayOf("react/components"), extensions = arrayOf("html"))
@Properties(Property(name = "service.description", value = "Hello World Type Servlet"), Property(name = "service.vendor", value = "The Apache Software Foundation"))
class ByResourceTypeServlet : SlingSafeMethodsServlet() {

    private val log = LoggerFactory.getLogger(ByResourceTypeServlet::class.java)

    @Reference
    private lateinit var react: ReactEngine

    @Reference
    private lateinit var beanSerializer: BeanSerializer

    private val SOURCE_PATH = "/etc/react-clientlibs/server.js"

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: SlingHttpServletRequest,
                       response: SlingHttpServletResponse) {
        val writer = response.writer
        val html = renderElement(request)
        response.contentType = "text/html"
        writer.append(html)
    }

    fun renderElement(request: SlingHttpServletRequest): String {
        val controller = request.adaptTo(IReactController::class.java)
        val props = beanSerializer.convertToMap(controller!!)
        return react.render(props, SOURCE_PATH)
    }

}

