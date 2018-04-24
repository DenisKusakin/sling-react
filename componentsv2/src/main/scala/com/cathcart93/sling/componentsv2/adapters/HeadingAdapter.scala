package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models.HeadingModel
import com.cathcart93.sling.componentsv2.models.dialog._
import org.apache.sling.api.resource.Resource

object HeadingAdapter extends ResourceAdapter[HeadingModel] {
  override def adapt: Resource => Option[HeadingModel] = { resource: Resource =>
    val valueMap = resource.getValueMap
    val text = valueMap.get("text", classOf[String])
    val size = valueMap.get("size", classOf[Integer])
    val fit = valueMap.get("fit", classOf[java.lang.Boolean])
    val lineHeight = valueMap.get("lineHeight", classOf[Integer])

    val dialogProps = Seq(
      TextProp(name = "text", value = text, title = "Text"),
      CheckboxProperty(name = "fit", value = fit, title = "Fit"),
      SelectProperty(
        name = "size",
        title = "Size",
        value = Option(size).map(x => s"$x").getOrElse("1"),
        options = (1 to 5).map(x => SelectPropertyOption(value = s"$x", title = s"H$x"))),
      SelectProperty(
        name = "height",
        value = "1",
        title = "Height",
        options = (1 to 4).map(x => SelectPropertyOption(value = s"$x", title = s"$x"))
      )
    )
    val dialog = SimpleDialogModel(path = resource.getPath, props = dialogProps)

    Some(HeadingModel(text = text, size = size, fit = fit, lineHeight = lineHeight, dialog = Some(dialog)))
  }
}
