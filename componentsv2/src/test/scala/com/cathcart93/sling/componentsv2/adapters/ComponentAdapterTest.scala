package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdaptable._
import org.apache.sling.api.resource.{Resource, ValueMap}
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}
import com.cathcart93.sling.componentsv2.models.{Component => ComponentModel, HeadingModel}
import org.scalatest.Assertions._

class ComponentAdapterTest extends FlatSpec with Matchers with MockitoSugar with Implicits {
  "A ComponentAdapter" should "adapt according to component prop" in {
    val resourceMock = mock[Resource]
    val valueMapMock = mock[ValueMap]
    when(valueMapMock.get("component", classOf[String])).thenReturn("Heading")
    when(valueMapMock.get("text", classOf[String])).thenReturn("Text")
    when(valueMapMock.get("size", classOf[Int])).thenReturn(3)
    when(valueMapMock.get("fit", classOf[Boolean])).thenReturn(false)
    when(valueMapMock.get("lineHeight", classOf[Int])).thenReturn(2)
    when(resourceMock.getValueMap).thenReturn(valueMapMock)

    val adaptationResult = resourceMock.adapt[ComponentModel]
    val expected = HeadingModel(text = "text", size = 3, fit = false, lineHeight = 2)
    adaptationResult should be (expected)
  }
}
