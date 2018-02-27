package com.cathcart93.sling.componentsv2

import org.apache.sling.api.resource.Resource

trait ResourceAdapter[T] {
  def adapt: Resource => T
}
