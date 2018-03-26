package com.cathcart93.sling.componentsv2.adapters

trait Implicits {
  implicit val headingAdapter: HeadingAdapter.type = HeadingAdapter
  implicit val componentAdapter: ComponentAdapter.type = ComponentAdapter
}
