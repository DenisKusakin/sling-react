package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models.HeadingModel
import org.apache.sling.api.resource.Resource

object HeadingAdapter extends ResourceAdapter[HeadingModel] {
  override def adapt: Resource => HeadingModel = (resource: Resource) => {
    val valueMap = resource.getValueMap
    val text = valueMap.get("text", classOf[String])
    val size = valueMap.get("size", classOf[Int])
    val fit = valueMap.get("fit", classOf[Boolean])
    val lineHeight = valueMap.get("lineHeight", classOf[Int])

    HeadingModel(text = text, size = size, fit = fit, lineHeight = lineHeight)
  }
}
