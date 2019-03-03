package com.cathcart93.sling.components

import org.apache.commons.io.IOUtils
import org.apache.felix.scr.annotations.Properties
import org.apache.felix.scr.annotations.Property
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.slf4j.LoggerFactory
import java.net.HttpURLConnection
import java.net.URL

@SlingServlet(resourceTypes = ["sling-react/next"], extensions = ["html"], generateService = true)
@Properties(
        Property(name = "service.description", value = ["Next Render Servlet"]),
        Property(name = "service.vendor", value = ["Denis Kusakin"])
)
class NextJSRenderServlet : SlingSafeMethodsServlet() {

    private val log = LoggerFactory.getLogger(NextJSRenderServlet::class.java)

    override fun doGet(request: SlingHttpServletRequest,
                       response: SlingHttpServletResponse) {
        val nextJsPage = request.resource.adaptTo(NextJSEndpoint::class.java)
        if (nextJsPage == null) {
            response.setStatus(500)
            response.writer.write("Failed to resolve NextJS endpoint")
            return
        }
        val nextJsConnection = URL(nextJsPage.renderUrl()).openConnection() as HttpURLConnection
        response.contentType = nextJsConnection.contentType
        response.setStatus(nextJsConnection.responseCode)

        val responseOs = response.outputStream
        val nextJsIs = nextJsConnection.inputStream
        IOUtils.copy(nextJsIs, responseOs)
        nextJsIs.close()
        responseOs.close()
    }

}

