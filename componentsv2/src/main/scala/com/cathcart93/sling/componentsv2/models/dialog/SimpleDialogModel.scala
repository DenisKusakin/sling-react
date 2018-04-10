package com.cathcart93.sling.componentsv2.models.dialog

import com.cathcart93.sling.componentsv2.models.Component

case class SimpleDialogModel[T <: Component](path: String, props: Seq[SimpleDialogProp], component: Option[T]) extends Component