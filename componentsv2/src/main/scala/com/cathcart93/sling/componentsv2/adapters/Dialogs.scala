package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.models.dialog._
import org.apache.sling.api.resource.Resource

/**
  * @author Denis_Kusakin. 4/10/2018.
  */
trait Dialogs extends ResourceAdaptableImplicit{
  val headingDialog: Resource => Seq[SimpleDialogProp] = { resource: Resource =>
    val headingModel = resource.adapt(HeadingAdapter)
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
}
