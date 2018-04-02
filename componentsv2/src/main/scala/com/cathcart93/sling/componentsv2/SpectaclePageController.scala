package com.cathcart93.sling.componentsv2

import javax.annotation.PostConstruct
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject

@Model(
  adaptables = Array(classOf[Resource], classOf[SlingHttpServletRequest]),
  resourceType = Array("sling-react/client-page")
)
class SpectaclePageController {
  @SlingObject
  private var resource: Resource = _

  @PostConstruct
  def init(): Unit = {

  }

  def getData: String = {
    ???
  }
}
