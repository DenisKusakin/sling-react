package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import org.apache.sling.api.resource.Resource

trait ResourceAdaptableImplicits {
  implicit class AdaptableResource(resource: Resource) {
    def adapt[T](implicit adapter: ResourceAdapter[T]): T = {
      adapter.adapt(resource)
    }
  }

  implicit val headingAdapter: HeadingAdapter.type = HeadingAdapter
  implicit val componentAdapter: ComponentAdapter.type = ComponentAdapter
  implicit val slideAdapter: SlideAdapter.type = SlideAdapter
}
