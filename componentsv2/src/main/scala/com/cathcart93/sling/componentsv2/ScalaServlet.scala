package com.cathcart93.sling.componentsv2

import org.apache.felix.scr.annotations.{Properties, Property}
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.{SlingHttpServletRequest, SlingHttpServletResponse}
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import com.cathcart93.sling.componentsv2.adapters.{HeadingAdapter, ResourceAdaptableImplicit, SpectacleAdapters, SpectacleComponentAdapter}
import com.cathcart93.sling.componentsv2.json.ReactToJsonConverters
import com.cathcart93.sling.componentsv2.models.{DeckModel, HeadingModel}
import com.cathcart93.sling.componentsv2.react.{ErrorReactComponent, ReactComponent, SpectacleReactAdapters}
import org.json4s.NoTypeHints
import org.json4s.native.Serialization.{write => swrite}

@SlingServlet(
  extensions = Array("json"),
  generateComponent = true,
  resourceTypes = Array("sling-react/client-page"),
  methods = Array("GET"))
@Properties(
  Array(new Property(name = "service.description", value = Array("Scala Test Servlet")),
    new Property(name = "service.vendor", value = Array("Cathcart 93")))
)
class ScalaServlet extends SlingSafeMethodsServlet with SpectacleAdapters
  with SpectacleReactAdapters with ReactToJsonConverters {
  implicit val formats = org.json4s.native.Serialization.formats(NoTypeHints)

  override def doGet(request: SlingHttpServletRequest, response: SlingHttpServletResponse): Unit = {
    val resource = request.getResource
    val deck = resource.adapt[DeckModel]
    val deckReact: ReactComponent = deck
      .map(componentToReactAdapter.toReact)
      .getOrElse(ErrorReactComponent("Error"))
    response.getWriter.append(swrite(reactToJValue(deckReact)))
    response.setContentType("json")
  }
}
