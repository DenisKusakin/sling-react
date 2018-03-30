package com.cathcart93.sling.componentsv2.json

import com.cathcart93.sling.componentsv2.models.{Component, HeadingModel}
import com.cathcart93.sling.componentsv2.react.{IntProp, ObjectProp, React, StringProp}

trait Converters {

  import org.json4s._
  import org.json4s.JsonDSL._
  import org.json4s.native.JsonMethods._

  implicit val reactStringPropToJValue: StringProp => JString = (prop: StringProp) => JString(prop.value)
  implicit val reactIntPropToJValue: IntProp => JInt = (prop: IntProp) => JInt(prop.value)
  implicit val reactObjectPropToJValue: ObjectProp => JValue = (prop: ObjectProp) => {
    
  }

  implicit val reactToJValue: HeadingModel => JObject = (reactComponent: React) => {

    ???
  }
}
