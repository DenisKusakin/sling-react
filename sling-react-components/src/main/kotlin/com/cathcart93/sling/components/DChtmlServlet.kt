/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.cathcart93.sling.components

import com.cathcart93.sling.components.v2.*
import org.apache.commons.io.IOUtils
import org.apache.felix.scr.annotations.Properties
import org.apache.felix.scr.annotations.Property
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.slf4j.LoggerFactory

@SlingServlet(resourceTypes = ["sling-react/page", "sling-react/client-page", "spectacle/meduza-spectacle", "sling-spectacle/client-page"], extensions = ["html"])
@Properties(Property(name = "service.description", value = "DC Html Servlet"), Property(name = "service.vendor", value = "Cathcart 93"))
class DChtmlServlet : SlingSafeMethodsServlet() {

    private val log = LoggerFactory.getLogger(DChtmlServlet::class.java)

    override fun doGet(request: SlingHttpServletRequest,
                       response: SlingHttpServletResponse) {
//        val writer = response.writer
//        val controller = request.adaptTo(PageController::class.java)
//        writer.append(controller!!.getProps())
        val authorJsUrl = "/etc/sling-spectacle/spectacle.client.author.js"
        val isPreviewMode = request.requestPathInfo.selectorString?.contains("preview") ?: false
        val resource = request.resource
        val images = mutableSetOf<String>()
        val ImageContext = ImageSrcContext(buildUrl = { src ->
            images += src
            src
        })

        val element = (resource.valueMap.asString("xml"))?.let {
            MeduzaSpectacle { it }
        } ?: withContext(ImageContext) {
            withContext(EditModeContext(!isPreviewMode), {
                DeckComponent {
                    resource
                }
            })
        }

        val pageElement = SpectaclePage {
            SpectaclePageProps(content = element, jsUrl = authorJsUrl)
        }

        val htmlRenderer = RecursiveHtmlRenderer()
        val html = htmlRenderer.render(pageElement)
        response.contentType = "text/html; charset=utf-8"
        response.setStatus(200)
        val os = response.outputStream
        val iS = IOUtils.toInputStream(html, "UTF-8")

        IOUtils.copy(iS, os)
        iS.close()
        os.close()
//        response.contentType = "text/html; charset=utf-8"
//        response.setStatus(200)
    }

}

