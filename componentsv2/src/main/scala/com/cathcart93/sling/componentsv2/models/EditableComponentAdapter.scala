package com.cathcart93.sling.componentsv2.models

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models.dialog.{SimpleDialogModel, SimpleDialogProp}
import org.apache.sling.api.resource.Resource

trait EditableComponentAdapter[T <: Component] extends ResourceAdapter[SimpleDialogModel[T]]{
  def dialog(resource: Resource): Seq[SimpleDialogProp]
  def component(resource: Resource): Option[T]

  override def adapt: Resource => Option[SimpleDialogModel[T]] = {resource: Resource =>
    Some(SimpleDialogModel(path = resource.getPath, props = dialog(resource), component = component(resource)))
  }
}
