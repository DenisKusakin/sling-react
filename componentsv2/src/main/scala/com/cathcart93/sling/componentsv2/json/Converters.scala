package com.cathcart93.sling.componentsv2.json

import com.cathcart93.sling.componentsv2.models.{Component, HeadingModel}
import com.cathcart93.sling.componentsv2.react._

trait Converters {

  import org.json4s._
  import org.json4s.JsonDSL._
  import org.json4s.native.JsonMethods._

  val reactStringPropToJValue: StringProp => JString = (prop: StringProp) => JString(prop.value)
  val reactIntPropToJValue: IntProp => JInt = (prop: IntProp) => JInt(prop.value)
  val reactObjectPropToJValue: ObjectProp => JObject = (prop: ObjectProp) => {
    prop.props
  }
  implicit val reactPropToJValue: ReactPropValue => JValue = {
    case x: StringProp => reactStringPropToJValue(x)
    case x: IntProp => reactIntPropToJValue(x)
    case x: ObjectProp => reactObjectPropToJValue(x)
  }

  implicit val reactToJValue: ReactComponent => JObject = (reactComponent: ReactComponent) => {
    ("componentName" -> reactComponent.componentName) ~
      ("props" -> reactComponent.props) ~
      ("children" -> JArray(reactComponent.children.map(x => reactPropToJValue(x)).toList))
  }
}
