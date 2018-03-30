package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models.{DeckModel, SlideModel, Component => ComponentModel}
import org.apache.sling.api.resource.Resource

import scala.collection.JavaConverters._

object DeckAdapter extends ResourceAdapter[DeckModel] with ResourceAdaptableImplicit {
  implicit val slideAdapter: ResourceAdapter[SlideModel] = SlideAdapter

  override def adapt: Resource => DeckModel = resource => {
    val components = resource.getChildren.asScala.map(x => x.adapt[SlideModel]).toSeq
    DeckModel(slides = components)
  }
}
