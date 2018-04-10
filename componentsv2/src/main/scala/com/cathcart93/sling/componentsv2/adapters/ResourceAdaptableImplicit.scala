package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import org.apache.sling.api.resource.Resource

trait ResourceAdaptableImplicit {
  implicit class AdaptableResource(resource: Resource) {
    def adapt[T](implicit adapter: ResourceAdapter[T]): Option[T] = {
      adapter.adapt(resource)
    }
  }

  class Wrapper{

  }
}
