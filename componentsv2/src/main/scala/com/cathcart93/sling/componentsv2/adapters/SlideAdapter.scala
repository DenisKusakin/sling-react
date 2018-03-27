package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models.{SlideModel, Component => ComponentModel}
import org.apache.sling.api.resource.Resource

import scala.collection.JavaConverters._

object SlideAdapter extends ResourceAdapter[SlideModel] with ResourceAdaptableImplicits {
  override def adapt: Resource => SlideModel = resource => {
    val valueMap = resource.getValueMap
    val bgColor = valueMap.get("bgColor", classOf[String])
    val textColor = valueMap.get("textColor", classOf[String])
    val components = resource.getChildren.asScala.map(x => x.adapt[ComponentModel]).toSeq
    //.asScala.map(_ adapt[ComponentModel])//.toSeq
    SlideModel(bgColor = bgColor, textColor = textColor, components = components)
  }
}
