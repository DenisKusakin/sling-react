package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models.dialog.ColorProperty
import com.cathcart93.sling.componentsv2.models.{Color, DeckModel, SlideModel}
import org.apache.sling.api.resource.Resource

import scala.collection.JavaConverters._

object DeckAdapter extends ResourceAdapter[DeckModel] with ResourceAdaptableImplicit {
  implicit val slideAdapter: ResourceAdapter[SlideModel] = SlideAdapter

  override def adapt: Resource => Option[DeckModel] = resource => {
    val components = resource.getChildren.asScala
      .map(x => x.adapt[SlideModel])
      .filter(_.isDefined)
      .map(x => x.get)
      .toSeq

    val valueMap = resource.getValueMap

    def propNameToColor = {
      name: String =>
        Option(valueMap.get(name, classOf[String]))
          .flatMap(x => Color.valueOf(x))
    }

    val dialogProps = Seq(
      ColorProperty(name = "primaryColor", title = "Primary Color", value = )
    )

    Some(DeckModel(
      slides = components,
      primary = propNameToColor("primaryColor"),
      secondary = propNameToColor("secondaryColor"),
      tertiary = propNameToColor("tertiaryColor"),
      quarternary = propNameToColor("quarternaryColor")
    ))
  }
}
