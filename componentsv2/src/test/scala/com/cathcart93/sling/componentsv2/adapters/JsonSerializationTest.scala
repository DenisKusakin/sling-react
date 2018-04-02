package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.json.ReactToJsonConverters
import com.cathcart93.sling.componentsv2.react.{ReactComponent, ReactStringProp}
import org.json4s.{DefaultFormats, NoTypeHints}
import org.scalatest.{FlatSpec, Matchers}
import org.json4s.native.Serialization.{write => swrite}
/**
  * @author Denis_Kusakin. 3/30/2018.
  */
class JsonSerializationTest extends FlatSpec with Matchers with ReactToJsonConverters {
  implicit val formats = DefaultFormats

  "Json serializer" should "serialize React component" in {
    val react = ReactComponent("test", Map("p1" -> ReactStringProp("p1 val")))
    val json = reactToJValue(react)
    swrite(json) should be("{\"componentName\":\"test\",\"props\":{\"p1\":\"p1 val\"},\"children\":[]}")
  }
}
