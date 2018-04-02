package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models.{BlockQuote, Component, HeadingModel}
import org.apache.sling.api.resource.Resource

object SpectacleComponentAdapter extends ResourceAdapter[Component] with ResourceAdaptableImplicit {
  implicit val headingAdapter: ResourceAdapter[HeadingModel] = HeadingAdapter
  implicit val blockQuoteAdapter: ResourceAdapter[BlockQuote] = BlockQuoteAdapter

  override def adapt: Resource => Option[Component] = (resource: Resource) => {
    val valueMap = resource.getValueMap
    val componentName = valueMap.get("component", classOf[String])
    componentName match {
      case "Heading" => resource.adapt[HeadingModel]
      case "BlockQuote" => resource.adapt[BlockQuote]
      case _ => None
    }
  }
}
