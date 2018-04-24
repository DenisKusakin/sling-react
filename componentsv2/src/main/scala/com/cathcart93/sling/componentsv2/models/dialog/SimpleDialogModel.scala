package com.cathcart93.sling.componentsv2.models.dialog

class ComponentDialog
case class SimpleDialogModel(path: String, props: Seq[SimpleDialogProp]) extends ComponentDialog
case object EmptyDialog extends ComponentDialog