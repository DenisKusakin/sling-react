package com.cathcart93.sling.components.models.spectacle.templates

import com.cathcart93.sling.components.v2.*

object SingleComponentSlide : ComponentV2<Element> {
    override fun render(props: Element): Element {
        return Slide {
            components = listOf(props)
        }
    }

}

object TestRoot : ComponentV2<List<Element>> {
    override fun render(props: List<Element>): Element {
        return RootComponentV2 {
            RootComponentProps(
                    content = Deck {
                        controls = false
                        slides = props
                    },
                    url = "test"
            )
        }
    }

}

val testElement = TestRoot {
    (1..10).map {
        SingleComponentSlide {
            Text {
                text = "Slide V4 $it"
                textColor = "#FF0000"
            }
        }
    }
}