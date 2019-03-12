package com.cathcart93.sling.components.v2

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render.ReactRenderer
import org.junit.Test
import with
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals
import kotlin.test.assertFails

open class SimpleComponentTestsV2 {
    @Test
    fun test1() {
        val functionalComponent = createComponent { title: String ->
            h1(HProps(title))
        }

        val rootElement = functionalComponent("Test")

        val expectedRenderResult = createElement("components/h1", props {
            "title" to "Test"
        })

        val renderer = ReactRenderer()
        val renderResult = renderer.render(rootElement)

        assertEquals(expectedRenderResult, renderResult)
    }

    @Test
    fun test3() {
        val context = createContext<String>()
        val el2 = container {
            val elements = mutableListOf<Element>()
            listOf(
                    h1(HProps("First Header")),
                    context.provider("Value from context") {
                        context.consumer {
                            { valueFromContext: String ->
                                container {
                                    listOf(
                                            h2(HProps(valueFromContext)),
                                            h1(HProps("$valueFromContext Changed"))
                                    )
                                }
                            }
                        }
                    },
                    h1(HProps("Last Header"))
            )
        }
//        val el2 = container {
//            h1(HProps("First Header"))
//            context.provider("Value from context", context.consumer(NoProps) { valueFromContext: String ->
//                container {
//                    h2(HProps(valueFromContext))
//                    h1(HProps("$valueFromContext Changed"))
//                }
//            })
//        }

        val expectedRenderResult = "components/container".with(BasicElementProperty()) {
            +"components/h1".with(props {
                "title" to "First Header"
            })
            +"components/container".with(BasicElementProperty()) {
                +"components/h2".with(props {
                    "title" to "Value from context"
                })
                +"components/h1".with(props {
                    "title" to "Value from context Changed"
                })
            }
            +"components/h1".with(props {
                "title" to "Last Header"
            })
        }

        val renderer = ReactRenderer()
        val elRenderResult = renderer.render(el2)
        assertEquals(expectedRenderResult, elRenderResult)
    }

    @Test
    fun nestedComponentTest() {
        val element = container.with {
            +h1.with(HProps("Title 1"))
            +h2.with(HProps("Title 2"))
        }

        val expectedRenderResult = createElement("components/container", BasicElementProperty(), listOf(
                createElement("components/h1", props {
                    "title" to "Title 1"
                }),
                createElement("components/h2", props {
                    "title" to "Title 2"
                })
        ))

        val renderer = ReactRenderer()
        val renderResult = renderer.render(element)

        assertEquals(expectedRenderResult, renderResult)
    }

    @Test
    fun contextTest() {
        val context = createContext<String>()

        val component = createComponent<NoProps> {
            context.consumer.with {
                { titleFromContext: String ->
                    h1.with(HProps(titleFromContext))
                }
            }
        }

        val rootElement = context.provider.with("Value from context") {
            component.with(NoProps)
        }

        val expectedRenderResult = "components/h1".with(props {
            "title" to "Value from context"
        })
        val renderer = ReactRenderer()
        val result = renderer.render(rootElement)
        assertEquals(expectedRenderResult, result)
    }

    @Test
    fun multipleContextProviders() {
        val context = createContext<String>()

        val rootElement = context.provider.with("Top-level context provider") {
            container.with {
                +context.consumer.with {
                    { title: String ->
                        h1.with(HProps(title))
                    }
                }
                +context.provider.with("Value from context") {
                    context.consumer.with {
                        { titleFromContext: String ->
                            h1.with(HProps(titleFromContext))
                        }
                    }
                }
            }
        }

        val expectedRenderResult = "components/container".with(BasicElementProperty()) {
            +"components/h1".with(props {
                "title" to "Top-level context provider"
            })
            +"components/h1".with(props {
                "title" to "Value from context"
            })
        }

        val renderer = ReactRenderer()
        val result = renderer.render(rootElement)
        assertEquals(expectedRenderResult, result)
    }

    @Test
    fun contextNotProvided() {
        val context = createContext<String>()

        val rootElement = container.with {
            +context.consumer.with {
                { _: String ->
                    h1.with(HProps("Test"))
                }
            }
        }

        val renderer = ReactRenderer()

        assertFails("Exception should be thrown if no Context Provider defined") {
            renderer.render(rootElement)
        }
    }

    @Test
    fun contextNotProvided2() {
        val context = createContext<String>()
        val context2 = createContext<Int>()

        val rootElement = container.with {
            +context.provider.with("Test 2") {
                context2.consumer.with {
                    { _ ->
                        h1.with(HProps("Test"))
                    }
                }
            }
        }

        val renderer = ReactRenderer()

        assertFails("Exception should be thrown if no Context Provider defined") {
            renderer.render(rootElement)
        }
    }

    @Test
    fun hugeTree() {
        val element = container.with {
            (1..100000).forEach {
                +h1.with(HProps("H $it"))
            }
        }

        val renderer = ReactRenderer()

        val renderingTime = measureTimeMillis {
            val result = renderer.render(element)
            result
        }

        System.out.println(renderingTime)
    }

    @Test
    fun deepTree() {

        val element = createDeepElement(100)

        val renderer = ReactRenderer()

        val renderingTime = measureTimeMillis {
            val result = renderer.render(element)
            result
        }

        System.out.println(renderingTime)
    }

    private fun createDeepElement(count: Int): Element {
        return if (count > 0) {
            container.with {
                +(createDeepElement(count - 1))
            }
        } else {
            h1.with(HProps("Test"))
        }
    }

}