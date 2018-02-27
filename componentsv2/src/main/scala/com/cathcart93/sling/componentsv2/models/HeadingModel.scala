package com.cathcart93.sling.componentsv2.models

case class HeadingModel(
                         val text: String,
                         val size: Int,
                         val fit: Boolean,
                         val lineHeight: Int) extends SlideComponent {

}
