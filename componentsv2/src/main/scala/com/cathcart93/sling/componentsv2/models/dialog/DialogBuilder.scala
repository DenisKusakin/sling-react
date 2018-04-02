package com.cathcart93.sling.componentsv2.models.dialog

object DialogBuilder {

  class DialogContext() {
    val props: Seq[SimpleDialogProp] = Seq()

    def text(name: String, value: String, title: String): Unit = {
      props :+ TextProp(name = name, value = value, title = title)
    }
  }

  def dialog(path: String)(init: DialogContext => Unit): SimpleDialogModel = {
    val dialogContext = new DialogContext()
    init(dialogContext)
    SimpleDialogModel(path = path, dialogContext.props)
  }
}
