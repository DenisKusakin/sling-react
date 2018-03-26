package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.{ResourceAdaptable, ResourceAdapter}
import com.cathcart93.sling.componentsv2.models.{Component, HeadingModel}
import org.apache.sling.api.resource.Resource
import ResourceAdaptable._

object ComponentAdapter extends ResourceAdapter[Component]{
  private val map = Map[String, ResourceAdapter[Any]](
    "Heading" -> HeadingAdapter
  )
  override def adapt: Resource => Component = (resource: Resource) => {
    val valueMap = resource.getValueMap
    val componentName = valueMap.get("component", classOf[String])
    val adapter: ResourceAdapter[Component] = map(componentName)
    resource.adapt(adapter)
  }
}
