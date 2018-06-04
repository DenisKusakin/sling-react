package com.cathcart93.sling.components

import com.cathcart93.sling.components.models.spectacle.impl.builder.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.*
import org.junit.Assert
import org.junit.Test

open class Test {
    @Test
    fun test() {
        val deck = deck {
            slide {
                heading("Heading 1") {
                    italic = false
                }
            }
        }

        val expectedReactElement = ReactElement("Deck", children = listOf(
                ReactElement("Slide", children = listOf(
                        ReactElement("Heading", mapOf("italic" to BooleanProp(false)), listOf(StringProp("Heading 1")))
                ))
        ))

        Assert.assertEquals(expectedReactElement, deck.toReactElement())
    }

    @Test
    fun deckTest() {
        val deck = deck {
            transition = SlideTransition(listOf(ZoomTransitionType, FadeTransitionType))
            transitionDuration = 100
            progress = Pacman
        }

        val expectedReactElement = ReactElement(name = "Deck", props = mapOf(
                "transition" to StringProp("zoom fade"),
                "transitionDuration" to NumberProp(100),
                "progress" to StringProp("pacman")
        ), children = emptyList())

        Assert.assertEquals(expectedReactElement, deck.toReactElement())
    }

    @Test
    fun blockQuoteTest() {
        val blockQuote = blockQuote {
            quote("Test") {

            }
            cite("Test Cite") {

            }
        }

        val expectedReactElement = ReactElement(name = "BlockQuote", children = listOf(
                ReactElement(name = "Quote", children = listOf("Test".toReactProp())),
                ReactElement(name = "Cite", children = listOf("Test Cite".toReactProp()))
        ))
        Assert.assertEquals(expectedReactElement, blockQuote.toReactElement())
    }

    @Test
    fun appearTest() {
        val appear = appear(heading("Test") {
            italic = true
        }) {
            transitionDuration = 500
        }

        val expectedReactElement = ReactElement(name = "Appear", children = listOf(
                ReactElement(name = "Heading", children = listOf("Test".toReactProp()), props = mapOf("italic" to true.toReactProp()))
        ), props = mapOf("transitionDuration" to 500.toReactProp()))

        Assert.assertEquals(expectedReactElement, appear.toReactElement())
    }

    @Test
    fun linkTest() {
        val link = link("https://google.com", "Google") {
            italic = true
            bold = true
            textAlign = CenterAlign
        }
        val expectedReactElement = ReactElement(name = "Link", props = mapOf(
                "href" to "https://google.com".toReactProp(),
                "italic" to true.toReactProp(),
                "bold" to true.toReactProp(),
                "textAlign" to CenterAlign.toReactProp()
        ), children = listOf("Google".toReactProp()))

        Assert.assertEquals(expectedReactElement, link.toReactElement())
    }

    @Test
    fun testJson() {
        val element = ReactElement(name = "Test", props = mapOf(
                "k" to "v".toReactProp(),
                "r" to ReactElement(name = "Test2", props = mutableMapOf("x" to "y".toReactProp()))
        ), children = listOf(
                ReactElement(name = "Child")
        ))

        println(element.toMap())
    }
}