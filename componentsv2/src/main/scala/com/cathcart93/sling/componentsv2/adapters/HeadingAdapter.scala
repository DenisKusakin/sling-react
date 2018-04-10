package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.models.HeadingModel
import com.cathcart93.sling.componentsv2.models.dialog._
import org.apache.sling.api.resource.Resource

object HeadingAdapter extends EditableComponent[HeadingModel] {
  override def dialog(resource: Resource): Seq[SimpleDialogProp] = {
    val headingModel = component(resource)
    Seq(
      TextProp(name = "text", value = headingModel.map(_.text).getOrElse(""), title = "Text"),
      CheckboxProperty(name = "fit", value = headingModel.exists(_.fit), title = "Fit"),
      SelectProperty(
        name = "size",
        title = "Size",
        value = headingModel.map(x => s"${x.size}").getOrElse("1"),
        options = (1 to 5).map(x => SelectPropertyOption(value = s"$x", title = s"H$x"))),
      SelectProperty(
        name = "height",
        value = "1",
        title = "Height",
        options = (1 to 4).map(x => SelectPropertyOption(value = s"$x", title = s"$x"))
      )
    )
  }

  override def component(resource: Resource): Option[HeadingModel] = {
    val valueMap = resource.getValueMap
    val text = valueMap.get("text", classOf[String])
    val size = valueMap.get("size", classOf[Integer])
    val fit = valueMap.get("fit", classOf[java.lang.Boolean])
    val lineHeight = valueMap.get("lineHeight", classOf[Integer])

    Some(HeadingModel(text = text, size = size, fit = fit, lineHeight = lineHeight))
  }
}
