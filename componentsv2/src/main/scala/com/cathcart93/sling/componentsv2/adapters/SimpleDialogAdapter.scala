package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models.dialog.{SimpleDialogModel, SimpleDialogProp}
import org.apache.sling.api.resource.Resource

class SimpleDialogAdapter[T](componentAdapter: ResourceAdapter[T], dialog: Resource => Seq[SimpleDialogProp])
  extends ResourceAdapter[SimpleDialogModel[T]] {
  override def adapt: Resource => Option[SimpleDialogModel[T]] = { resource =>
    Some(SimpleDialogModel[T](path = resource.getPath, props = dialog(resource), component = componentAdapter(resource)))
  }
}
