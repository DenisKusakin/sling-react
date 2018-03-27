package com.cathcart93.sling.componentsv2.adapters

import org.apache.sling.api.resource.{Resource, ValueMap}
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}
import com.cathcart93.sling.componentsv2.models.{HeadingModel, Component => ComponentModel}
import org.apache.sling.testing.mock.sling.ResourceResolverType
import org.apache.sling.testing.mock.sling.builder.ContentBuilder
import org.apache.sling.testing.mock.sling.junit.SlingContext
import org.scalatest.Assertions._
import org.apache.sling.api.resource.ResourceResolver
import org.apache.sling.testing.mock.sling.MockSling
import scala.collection.JavaConverters._

class ComponentAdapterTest extends FlatSpec with Matchers with MockitoSugar with ResourceAdaptableImplicits {

  "A ComponentAdapter" should "adapt according to component prop" in {
    val context = new SlingContext(ResourceResolverType.RESOURCERESOLVER_MOCK)
    val props: Map[String, AnyRef] = Map(
      "component" -> "Heading",
      "text" -> "Text",
      "size" -> 3.asInstanceOf[Integer],
      "fit" -> false.asInstanceOf[java.lang.Boolean],
      "lineHeight" -> 2.asInstanceOf[Integer]
    )
    val resolver = MockSling.newResourceResolver
    val contentBuilder = new ContentBuilder(resolver)
    val resourceMock = contentBuilder
      .resource("/text", props.asJava)

    val adaptationResult = resourceMock.adapt[ComponentModel]
    val expected = HeadingModel(text = "Text", size = 3, fit = false, lineHeight = 2)
    adaptationResult should be (expected)
  }
}
