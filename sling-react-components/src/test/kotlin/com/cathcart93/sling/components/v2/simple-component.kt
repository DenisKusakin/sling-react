package com.cathcart93.sling.components.v2

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render.ReactRenderer
import org.junit.Test
import with
import kotlin.test.assertEquals

open class SimpleComponentTests {
    @Test
    fun test1() {
        val functionalComponent = createComponent { title: String ->
            h1.with(HProps(title))
        }

        val rootElement = functionalComponent.with("Test")

        val expectedRenderResult = createElement("components/h1", props {
            "title" to "Test"
        }, emptyList())

        val renderer = ReactRenderer()
        val renderResult = renderer.render(rootElement)

        assertEquals(expectedRenderResult, renderResult)
    }

    @Test
    fun nestedComponentTest() {
        val element = container.with(NoProps) {
            listOf(h1.with(HProps("Title 1")), h2.with(HProps("Title 2")))
        }

        val expectedRenderResult = createElement("components/container", BasicElementProperty(), listOf(
                createElement("components/h1", props {
                    "title" to "Title 1"
                }, emptyList()),
                createElement("components/h2", props {
                    "title" to "Title 2"
                }, emptyList())
        ))

        val renderer = ReactRenderer()
        val renderResult = renderer.render(element)

        assertEquals(expectedRenderResult, renderResult)
    }


}