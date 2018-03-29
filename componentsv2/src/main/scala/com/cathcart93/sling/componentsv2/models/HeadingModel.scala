package com.cathcart93.sling.componentsv2.models

case class HeadingModel(
                         text: String,
                         size: Int,
                         fit: Boolean,
                         lineHeight: Int) extends Component
