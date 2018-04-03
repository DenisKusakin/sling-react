package com.cathcart93.sling.componentsv2.adapters.authoring

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.adapters.{ResourceAdaptableImplicit, SpectacleAdapters}
import com.cathcart93.sling.componentsv2.models.HeadingModel
import com.cathcart93.sling.componentsv2.models.dialog._
import org.apache.sling.api.resource.Resource

object HeadingAuthoringAdapter extends ResourceAdapter[SimpleDialogModel] with ResourceAdaptableImplicit
  with SpectacleAdapters {
  override def adapt: Resource => Option[SimpleDialogModel] = (resource: Resource) => {
    val headingModel = resource.adapt[HeadingModel]
    val props = Seq(
      TextProp(name = "text", value = headingModel.map(_.text).getOrElse(""), title = "Text"),
      CheckboxProperty(name = "fit", value = headingModel.exists(_.fit), title = "Fit"),
      SelectProperty(
        name = "size",
        value = headingModel.map(x => s"${x.size}").getOrElse("1"),
        options = (1 to 5).map(x => SelectPropertyOption(value = s"$x", title = s"H$x"))),
      SelectProperty(
        name = "height",
        value = "1",
        options = (1 to 4).map(x => SelectPropertyOption(value = s"$x", title = s"$x"))
      )
    )
    headingModel.map(x => SimpleDialogModel(path = resource.getPath, props = props, component = x))
  }
}
