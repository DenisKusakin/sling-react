package com.cathcart93.sling.components.v2

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.ElementDescriptor
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.NoProps
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.createContext
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.createElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render.ReactJsonRenderer
import org.junit.Test
import kotlin.test.assertEquals

open class Test {
    val renderer = ReactJsonRenderer()

    data class HProps(val title: String)

    val h1: (String) -> ElementDescriptor = { title ->
        createElement("h1", HProps(title))
    }

    val h2: (String) -> ElementDescriptor = { title ->
        createElement("h2", HProps(title))
    }

    val container: (props: NoProps, List<ElementDescriptor>) -> ElementDescriptor = { _, children ->
        createElement(name = "div", children = children)
    }

    @Test
    fun test() {

        val functionalComponent: (String) -> ElementDescriptor = {
            createElement(h1, it)
        }

        val rootElement = createElement(functionalComponent, "Test")
        val result = renderer.render(rootElement)
        assertEquals("""{type: h1, props: {title: "Test"}}""", result)
    }

    @Test
    fun test2() {

        val functionalComponent: (String) -> ElementDescriptor = {
            createElement(h1, it)
        }

        val rootElement = createElement(
                container,
                NoProps,
                createElement(
                        functionalComponent,
                        "Test"
                )
        )
        val result = renderer.render(rootElement)
        assertEquals("""{type: h1, props: {title: "Test"}}""", result)
    }

    @Test
    fun test3() {
        val context = createContext<String>()
        val component: () -> ElementDescriptor = {
            createElement(
                    container,
                    NoProps,
                    createElement(
                            h1,
                            "First Header"
                    ),
                    createElement(
                            context.provider,
                            "Value from context",
                            createElement(
                                    context.consumer,
                                    { valueFromContext ->
                                        createElement(
                                                h2,
                                                valueFromContext
                                        )
                                    }
                            )
                    ),
                    createElement(
                            h1,
                            "Last Header"
                    )
            )
        }
        val rootElement = createElement(component)
        renderer.render(rootElement)
    }

    @Test
    fun contextTest() {
        val context = createContext<String>()

        val functionalComponent: (String) -> ElementDescriptor = {
            createElement(context.consumer, { titleFromContext ->
                createElement(h1, titleFromContext)
            })
        }


        val rootElement = createElement(
                context.provider,
                "Value from context",
                createElement(
                        functionalComponent,
                        "Test"
                )
        )

        val result = renderer.render(rootElement)
        assertEquals("""{type: h1, props: {title: "Test"}}""", result)
    }
}