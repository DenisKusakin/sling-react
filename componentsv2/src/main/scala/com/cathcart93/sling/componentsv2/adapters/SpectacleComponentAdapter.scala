package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models.{BlockQuote, Component, DeckModel, HeadingModel}
import org.apache.sling.api.resource.Resource

object SpectacleComponentAdapter extends ResourceAdapter[Component] with ResourceAdaptableImplicit {

  override def adapt: Resource => Option[Component] = (resource: Resource) => {
    val valueMap = resource.getValueMap
    val componentName = valueMap.get("component", classOf[String])
    val adapter: Option[ResourceAdapter[_ <: Component]] = componentName match {
      case "Heading" => Some(HeadingAdapter)
      case "BlockQuote" => Some(BlockQuoteAdapter)
      case "Deck" => Some(DeckAdapter)
      case _ => None
    }
    adapter.flatMap(x => resource.adapt(x))
  }
}
