package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models.{EditableComponentAdapter, HeadingModel}
import org.apache.sling.api.resource.Resource

object HeadingAdapter extends ResourceAdapter[HeadingModel] {
  override def adapt: Resource => Option[HeadingModel] = { resource: Resource =>
    val valueMap = resource.getValueMap
    val text = valueMap.get("text", classOf[String])
    val size = valueMap.get("size", classOf[Integer])
    val fit = valueMap.get("fit", classOf[java.lang.Boolean])
    val lineHeight = valueMap.get("lineHeight", classOf[Integer])

    Some(HeadingModel(text = text, size = size, fit = fit, lineHeight = lineHeight))
  }
}
