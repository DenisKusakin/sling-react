package com.cathcart93.sling.componentsv2.json

import com.cathcart93.sling.componentsv2.react._

trait ReactToJsonConverters {

  import org.json4s._
  import org.json4s.JsonDSL._

  private val reactPropToJValue: ReactPropValue => JValue = {
    case x: ReactStringProp => x.value
    case x: ReactIntProp => x.value
    case x: ReactBooleanProp => x.value
    case x: ReactObjectProp => x.props.map(y => (y._1, reactPropToJValue(y._2)))
    case reactComponentProp: ReactComponentProp => reactToJValue(reactComponentProp.component)
  }

  val reactToJValue: ReactComponent => JValue = (reactComponent: ReactComponent) => {
    ("componentName" -> reactComponent.componentName) ~
      ("props" -> reactComponent.props.map(x => (x._1, reactPropToJValue(x._2)))) ~
      ("children" -> JArray(reactComponent.children.map(x => reactPropToJValue(x)).toList))
  }
}
