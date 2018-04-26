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

    val primary = propNameToColor("primaryColor")
    val secondary = propNameToColor("secondaryColor")
    val tertiary = propNameToColor("tertiaryColor")
    val quarternary = propNameToColor("quarternaryColor")

    val dialogProps = Seq(
      ColorProperty(name = "primaryColor", title = "Primary Color", value = primary),
      ColorProperty(name = "secondaryColor", title = "Secondary Color", value = secondary),
      ColorProperty(name = "tertiaryColor", title = "Tertiary Color", value = tertiary),
      ColorProperty(name = "quarternaryColor", title = "Qertiary Color", value = quarternary)
    )

    Some(DeckModel(
      slides = components,
      primary = primary,
      secondary = secondary,
      tertiary = tertiary,
      quarternary = quarternary
    ))
  }
}
