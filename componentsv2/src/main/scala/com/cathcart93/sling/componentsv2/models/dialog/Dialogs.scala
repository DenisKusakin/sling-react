package com.cathcart93.sling.componentsv2.models.dialog

import com.cathcart93.sling.componentsv2.adapters.{ResourceAdaptableImplicit, SpectacleAdapters}
import com.cathcart93.sling.componentsv2.models.HeadingModel
import org.apache.sling.api.resource.Resource
import DialogBuilder.dialog

trait Dialogs extends ResourceAdaptableImplicit with SpectacleAdapters {
  val headingDialog: Resource => SimpleDialogModel = (resource: Resource) => {
    val headingModel = resource.adapt[HeadingModel]
    dialog(resource.getPath) {
      text("", "", "")
    }
    ???
    //    val props = Seq(
    //      TextProp(name = "text", value = headingModel.map(_.text).getOrElse(""), title = "Text"),
    //      CheckboxProperty(name = "fit", value = headingModel.exists(_.fit), title = "Fit")
    //    )
    //    SimpleDialogModel(path = resource.getPath, props)
  }
}
