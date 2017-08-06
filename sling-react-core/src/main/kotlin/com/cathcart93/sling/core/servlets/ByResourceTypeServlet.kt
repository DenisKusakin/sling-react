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
package com.cathcart93.sling.core.servlets

import com.cathcart93.sling.core.services.React
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
    private val react: React? = null

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: SlingHttpServletRequest,
                       response: SlingHttpServletResponse) {
        val writer = response.writer
        writer.append(react!!.renderElement(request))
        response.contentType = "text/html"

    }

}

