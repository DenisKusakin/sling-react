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

import com.cathcart93.sling.components.models.PageController
import org.apache.felix.scr.annotations.Properties
import org.apache.felix.scr.annotations.Property
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.slf4j.LoggerFactory

@SlingServlet(resourceTypes = ["sling-react/page", "sling-react/client-page", "spectacle/meduza-spectacle"], extensions = ["json"])
@Properties(Property(name = "service.description", value = "Props Servlet"), Property(name = "service.vendor", value = "Cathcart 93"))
class PropsServlet : SlingSafeMethodsServlet() {

    private val log = LoggerFactory.getLogger(PropsServlet::class.java)

    override fun doGet(request: SlingHttpServletRequest,
                       response: SlingHttpServletResponse) {
        val writer = response.writer
        val controller = request.adaptTo(PageController::class.java)
        writer.append(controller!!.getProps())
        response.contentType = "json"
        //TODO: rework
        response.setHeader("Access-Control-Allow-Origin", "*")
    }

}

