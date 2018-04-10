package com.cathcart93.sling.componentsv2.react

import com.cathcart93.sling.componentsv2.models.Component

/**
  * @author Denis_Kusakin. 4/10/2018.
  */
trait ReactAdaptableImplicit {

  implicit class AdaptableComponent[T <: Component](component: T) {
    def toReact(implicit adapter: ModelToReactAdapter[T]): ReactComponent = {
      adapter.toReact(component)
    }
  }

}
