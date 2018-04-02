package com.cathcart93.sling.componentsv2.react

object ErrorReactComponent{
  def apply(msg: String): ReactComponent = {
    ReactComponent(componentName = "Error", props = Map("message" -> ReactStringProp(msg)))
  }
}
