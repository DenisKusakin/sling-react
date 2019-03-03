package com.cathcart93.sling.components

import com.cathcart93.sling.components.models.spectacle.impl.builder.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.*
import org.junit.Assert
import org.junit.Test

open class SpectacleTagsTest {
    private val colors = mutableMapOf(
            "primary" to DarkTheme.primaryColor?.toReactProp(),
            "secondary" to DarkTheme.secondaryColor?.toReactProp(),
            "tertiary" to DarkTheme.tertiaryColor?.toReactProp(),
            "quarternary" to DarkTheme.quarternaryColor?.toReactProp()
    ).filter { it.value != null }.map { it.key to it.value!! }.toMap()
    private val fonts = mutableMapOf(
            "primary" to DarkTheme.primaryFont?.toReactProp(),
            "secondary" to DarkTheme.secondaryFont?.toReactProp()
    ).filter { it.value != null }.map { it.key to it.value!! }.toMap()

    private val firstThemeColorsFonts = mapOf(
            "colors" to ObjectProps(colors),
            "fonts" to ObjectProps(fonts)
    )

    @Test
    fun simpleSlideWithHeading() {
        val deck = deck(DarkTheme) {
            slide {
                heading("Heading 1") {
                    italic = false
                }
            }
        }

        val expectedReactElement = ReactElement("Deck", props = firstThemeColorsFonts, children = listOf(
                ReactElement("Slide", children = listOf(
                        ReactElement("Heading", mapOf("italic" to BooleanProp(false)), StringProp("Heading 1"))
                ))
        ))

        Assert.assertEquals(expectedReactElement, deck.render())
    }

    @Test
    fun deckWithProperties() {
        val deck = deck(DarkTheme) {
            transition = SlideTransition(listOf(ZoomTransitionType, FadeTransitionType))
            transitionDuration = 100
            progress = Pacman
        }

        val expectedProps = mutableMapOf(
                "transition" to ArrayProp(listOf("zoom", "fade").map { StringProp(it) }),
                "transitionDuration" to NumberProp(100),
                "progress" to StringProp("pacman")
        )
        expectedProps.putAll(firstThemeColorsFonts);

        val expectedReactElement = ReactElement(name = "Deck", props = expectedProps, children = emptyList())

        Assert.assertEquals(expectedReactElement, deck.render())
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
                ReactElement(name = "Quote", children = "Test".toReactProp()),
                ReactElement(name = "Cite", children = "Test Cite".toReactProp())
        ))
        Assert.assertEquals(expectedReactElement, blockQuote.render())
    }

    @Test
    fun appearTest() {
        val appear = appear(heading("Test") {
            italic = true
        }) {
            transitionDuration = 500
        }

        val expectedReactElement = ReactElement(
                name = "Appear",
                children = ReactElement(
                        name = "Heading",
                        children = "Test".toReactProp(),
                        props = mapOf("italic" to true.toReactProp())
                ),
                props = mapOf("transitionDuration" to 500.toReactProp()))

        Assert.assertEquals(expectedReactElement, appear.render())
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
        ), children = "Google".toReactProp())

        Assert.assertEquals(expectedReactElement, link.render())
    }
}