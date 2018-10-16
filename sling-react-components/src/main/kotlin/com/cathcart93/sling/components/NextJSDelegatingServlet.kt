package com.cathcart93.sling.components

import org.apache.commons.io.IOUtils.copy
import org.apache.felix.scr.annotations.Properties
import org.apache.felix.scr.annotations.Property
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.slf4j.LoggerFactory
import java.net.HttpURLConnection
import java.net.URL

@SlingServlet(resourceTypes = ["sling-react/next/delegate"])
@Properties(
        Property(name = "service.description", value = ["Next JS Delegating Servlet"]),
        Property(name = "service.vendor", value = ["Denis Kusakin"])
)
class NextJSDelegatingServlet : SlingSafeMethodsServlet() {

    private val log = LoggerFactory.getLogger(NextJSDelegatingServlet::class.java)

    override fun doGet(request: SlingHttpServletRequest,
                       response: SlingHttpServletResponse) {
        val nextJsPage = request.resource.adaptTo(NextJSEndpoint::class.java)
        if (nextJsPage == null) {
            response.setStatus(500)
            response.writer.write("Failed to resolve NextJS endpoint")
            return
        }
        val queryString = if (request.queryString == null) "" else "?${request.queryString}"
        val urlString = "${nextJsPage.baseUrl()}${request.requestPathInfo.suffix}$queryString"
        val nextJsConnection = URL(urlString).openConnection() as HttpURLConnection
        val nextJsResponse = nextJsConnection.inputStream
        val responseOs = response.outputStream
        response.contentType = nextJsConnection.contentType
        response.setStatus(nextJsConnection.responseCode)
        //TODO: rework
        copy(nextJsResponse, responseOs)
        nextJsResponse.close()
        responseOs.close()
    }

}

