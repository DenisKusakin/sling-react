package com.cathcart93.sling.componentsv2.react

trait ModelToReactAdapter[T] {
  def toReact(model: T): ReactComponent
}
