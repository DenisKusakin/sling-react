package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.json.Converters
import com.cathcart93.sling.componentsv2.react.{ReactComponent, StringProp}
import org.json4s.NoTypeHints
import org.scalatest.{FlatSpec, Matchers}
import org.json4s.native.Serialization.{write => swrite}
/**
  * @author Denis_Kusakin. 3/30/2018.
  */
class JsonTest extends FlatSpec with Matchers with Converters {
  implicit val formats = org.json4s.native.Serialization.formats(NoTypeHints)

  "Json serializer" should "serialize React component" in {
    val map = Map("a" -> "1", "b" -> Map("a" -> "11"))
    val react = ReactComponent("test", Map("p1" -> StringProp("p1 val")))
    println(swrite(react))
  }
}
