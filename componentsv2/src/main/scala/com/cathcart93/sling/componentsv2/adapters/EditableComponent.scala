package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models.Component
import com.cathcart93.sling.componentsv2.models.dialog.{SimpleDialogModel, SimpleDialogProp}
import org.apache.sling.api.resource.Resource

trait EditableComponent[T <: Component] extends ResourceAdapter[SimpleDialogModel]{
  def dialog(resource: Resource): Seq[SimpleDialogProp]
  def component(resource: Resource): Option[T]

  override def adapt: Resource => Option[SimpleDialogModel] = {resource: Resource =>
    Some(SimpleDialogModel(path = resource.getPath, props = dialog(resource), component = component(resource)))
  }
}
