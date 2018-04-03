package com.cathcart93.sling.componentsv2.adapters.authoring

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.adapters.{ResourceAdaptableImplicit, SlideAdapter, SpectacleAdapters}
import com.cathcart93.sling.componentsv2.models.dialog.{SelectProperty, SelectPropertyOption, SimpleDialogModel}
import org.apache.sling.api.resource.Resource

object SlideAuthoringAdapter extends ResourceAdapter[SimpleDialogModel] with ResourceAdaptableImplicit {
  override def adapt: Resource => Option[SimpleDialogModel] = resource => {
    val slideModel = resource.adapt(SlideAdapter)
    val props = Seq(
      SelectProperty(
        name = "bgColor",
        title = "Background Color",
        value = slideModel.map(_.bgColor).getOrElse(""),
        options = Seq(
          SelectPropertyOption(title = "Primary", value = "primary"),
          SelectPropertyOption(title = "Secondary", value = "secondary")
        )
      )
    )
    slideModel.map(x => SimpleDialogModel(path = resource.getPath, props = props, component = x))
  }
}
