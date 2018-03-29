package com.cathcart93.sling.componentsv2.models

case class SlideModel(
                  bgColor: String,
                  textColor: String,
                  components: Seq[Any]) extends Component
