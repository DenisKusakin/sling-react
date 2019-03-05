package com.cathcart93.sling.components.v2

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render.ReactJsonRenderer
import invoke
import org.junit.Test
import with
import kotlin.test.assertEquals

open class Test {
    val renderer = ReactJsonRenderer()

    data class HProps(val title: String)

    val h1 = createComponent { title: String ->
        createElement("h1", HProps(title))
    }

    val h2 = createComponent { title: String ->
        createElement("h2", HProps(title))
    }

    val container = createComponent { _: NoProps, children: List<ElementDescriptor> ->
        createElement(name = "div", children = children, props = NoProps)
    }

    val noProps = createComponent { x: NoProps, y: NoProps ->
        createElement("h1", "Text")
    }

    @Test
    fun test() {

        val functionalComponent = createComponent { title: String ->
            createElement(h1, title, NoProps)
        }

        val rootElement = createElement(functionalComponent, "Test", NoProps)
        val result = renderer.render(rootElement)
        assertEquals("""{type: h1, props: {title: "Test"}}""", result)
    }

    @Test
    fun test2() {

        val functionalComponent = createComponent<String> {
            createElement(h1, it, NoProps)
        }

        val rootElement = createElement(
                container,
                NoProps,
                listOf(createElement(
                        functionalComponent,
                        "Test",
                        NoProps
                ))
        )
        val result = renderer.render(rootElement)
        assertEquals("""{type: h1, props: {title: "Test"}}""", result)
    }

    @Test
    fun test3() {
        val context = createContext<String>()
        val component = createComponent {
            createElement(
                    container,
                    NoProps,
                    listOf(
                            createElement(
                                    h1,
                                    "First Header", NoProps
                            ),
                            createElement(
                                    context.provider,
                                    "Value from context",
                                    createElement(
                                            context.consumer,
                                            NoProps,
                                            { valueFromContext: String ->
                                                createElement(
                                                        h2,
                                                        valueFromContext,
                                                        NoProps
                                                )
                                            }
                                    )
                            ),
                            createElement(
                                    h1,
                                    "Last Header",
                                    NoProps
                            )
                    )
            )
        }
        val rootElement = createElement(component, NoProps, NoProps)
        renderer.render(rootElement)
    }

    @Test
    fun contextTest() {
        val context = createContext<String>()

        val functionalComponent = createComponent<String> {
            createElement(context.consumer, NoProps, { titleFromContext: String ->
                createElement(h1, titleFromContext, NoProps)
            })
        }

        val re = context.provider.with("Value from Context") {
            functionalComponent.with("Test")
        }

        val rootElement = createElement(
                context.provider,
                "Value from context",
                createElement(
                        functionalComponent,
                        "Test",
                        NoProps
                )
        )

        val result = renderer.render(rootElement)
        assertEquals("""{type: h1, props: {title: "Test"}}""", result)
    }

    @Test
    fun test4() {
        val context = createContext<String>()
        val provider = context.provider
        val consumer = context.consumer
        provider.with("Value from context") {
            consumer {
                { title: String ->
                    h1.with(title)
                }
            }
        }
    }
}