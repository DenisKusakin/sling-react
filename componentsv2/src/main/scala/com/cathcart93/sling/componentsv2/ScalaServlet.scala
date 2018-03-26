package com.cathcart93.sling.componentsv2

import org.apache.felix.scr.annotations.{Properties, Property}
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.{SlingHttpServletRequest, SlingHttpServletResponse}
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import ResourceAdaptable._
import com.cathcart93.sling.componentsv2.adapters.{HeadingAdapter, Implicits}
import com.cathcart93.sling.componentsv2.models.HeadingModel

@SlingServlet(
  selectors = Array("scalaservlet"),
  extensions = Array("json"),
  generateComponent = true,
  resourceTypes=Array("sling/servlet/default"),
  methods = Array("GET"))
@Properties(
  Array(new Property(name = "service.description", value = Array("Scala Test Servlet")),
    new Property(name = "service.vendor", value = Array("Cathcart 93")))
)
class ScalaServlet extends SlingSafeMethodsServlet with Implicits {
  override def doGet(request: SlingHttpServletRequest, response: SlingHttpServletResponse): Unit = {
    val resource = request.getResource
    response.getWriter.append(resource.adapt[HeadingModel].toString)
    response.setContentType("json")
  }
}
