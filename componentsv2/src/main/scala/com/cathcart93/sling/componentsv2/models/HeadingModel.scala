package com.cathcart93.sling.componentsv2.models

import com.cathcart93.sling.componentsv2.models.dialog.{ComponentDialog, EmptyDialog, SimpleDialogModel}

case class HeadingModel(
                         text: String,
                         size: Int,
                         fit: Boolean,
                         lineHeight: Int,
                         dialog: Option[ComponentDialog] = None) extends Component
