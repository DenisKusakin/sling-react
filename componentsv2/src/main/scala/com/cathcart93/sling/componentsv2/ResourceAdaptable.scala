package com.cathcart93.sling.componentsv2

import org.apache.sling.api.resource.Resource

object ResourceAdaptable {

  implicit class AdaptableResource(resource: Resource) {
    def adaptUsing[T](adapter: ResourceAdapter[T]): T = {
      adapter.adapt(resource)
    }
  }

}
