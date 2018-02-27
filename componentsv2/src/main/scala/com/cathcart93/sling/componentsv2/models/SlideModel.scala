package com.cathcart93.sling.componentsv2.models

case class SlideModel(
                  val bgColor: String,
                  val textColor: String,
                  val components: Seq[SlideComponent]) {

}
