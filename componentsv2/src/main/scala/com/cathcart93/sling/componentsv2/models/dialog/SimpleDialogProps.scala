package com.cathcart93.sling.componentsv2.models.dialog

trait SimpleDialogProp
case class TextProp(name: String, value: String, title: String) extends SimpleDialogProp
case class MultilineTextProperty(name: String, value: String, title: String) extends SimpleDialogProp
case class CheckboxProperty(name: String, value: Boolean, title: String) extends SimpleDialogProp
case class SelectProperty(name: String, value: String, options: Seq[SelectPropertyOption]) extends SimpleDialogProp
case class SelectPropertyOption(value: String, title: String)
case class CodeProperty(name: String, value: String, title: String) extends SimpleDialogProp
case class ColorProperty(name: String, value: String, title: String) extends SimpleDialogProp
