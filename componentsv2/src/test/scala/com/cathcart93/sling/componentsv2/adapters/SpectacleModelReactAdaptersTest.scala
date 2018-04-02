package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.models.{BlockQuote, DeckModel, HeadingModel, SlideModel}
import com.cathcart93.sling.componentsv2.react._
import org.scalatest.{FlatSpec, Matchers}

class SpectacleModelReactAdaptersTest extends FlatSpec with Matchers with SpectacleReactAdapters {
  "Heading model" should "be adaptable to ReactComponent" in {
    val component = HeadingModel(text = "heading text", size = 1, fit = false, lineHeight = 2)
    val adapted = componentToReactAdapter.toReact(component)
    val expectedReact = ReactComponent(
      "Heading",
      Map(
        "size" -> ReactIntProp(1),
        "fit" -> ReactBooleanProp(false),
        "lineHeight" -> ReactIntProp(2)
      ),
      Seq(
        ReactStringProp("heading text")
      )
    )
    adapted should be(expectedReact)
  }
  "BlockQuote model" should "be adaptable to ReactComponent" in {
    val component = BlockQuote(cite = "cite", quote = "quote")
    val adapted = componentToReactAdapter.toReact(component)
    val expectedReact = ReactComponent(
      componentName = "BlockQuote",
      children = Seq(
        ReactComponentProp(
          ReactComponent(componentName = "Quote", children = Seq(ReactStringProp("quote")))
        ),
        ReactComponentProp(
          ReactComponent(componentName = "Cite", children = Seq(ReactStringProp("cite")))
        )
      )
    )
    adapted should be(expectedReact)
  }
  "DeckModel model" should "be adaptable to ReactComponent" in {
    val component = DeckModel(Seq(
      SlideModel(bgColor = "bgcolor", textColor = "textcolor", components = Seq(
        HeadingModel(text = "heading text", size = 1, fit = false, lineHeight = 2)
      ))
    ))
    val adapted = componentToReactAdapter.toReact(component)
    val expectedReact = ReactComponent(
      componentName = "Deck",
      children = Seq(
        ReactComponentProp(ReactComponent(
          componentName = "Slide",
          props = Map(
            "bgColor" -> ReactStringProp("bgcolor"),
            "textColor" -> ReactStringProp("textcolor")
          ),
          children = Seq(
            ReactComponentProp(
              ReactComponent(
                componentName = "Heading",
                props = Map(
                  "size" -> ReactIntProp(1),
                  "fit" -> ReactBooleanProp(false),
                  "lineHeight" -> ReactIntProp(2)
                ),
                children = Seq(ReactStringProp("heading text"))
              )
            )
          )
        ))
      )
    )
    adapted should be(expectedReact)
  }
}
