package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models.{SlideModel, Component => ComponentModel}
import org.apache.sling.api.resource.Resource

import scala.collection.JavaConverters._

object SlideAdapter extends ResourceAdapter[SlideModel] with ResourceAdaptableImplicit {
  implicit val componentAdapter: ResourceAdapter[ComponentModel] = SpectacleComponentAdapter

  override def adapt: Resource => Option[SlideModel] = resource => {
    val valueMap = resource.getValueMap
    val bgColor = valueMap.get("bgColor", classOf[String])
    val textColor = valueMap.get("textColor", classOf[String])
    val components = resource.getChildren.asScala
      .map(x => x.adapt[ComponentModel])
      .filter(_.isDefined)
      .map(_.get)
      .toSeq
    Some(SlideModel(bgColor = bgColor, textColor = textColor, components = components))
  }
}
