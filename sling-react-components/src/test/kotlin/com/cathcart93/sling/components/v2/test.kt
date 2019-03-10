package com.cathcart93.sling.components.v2

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render.ReactRenderer
import org.junit.Test
import with

open class Test {
    val renderer = ReactRenderer()

//    data class HProps(val title: String)

//    val h1 = createComponent { title: String ->
//        createElement("h1", HProps(title))
//    }
//
//    val h2 = createComponent { title: String ->
//        createElement("h2", HProps(title))
//    }
//
//    val container = createComponent { _: NoProps, childrenProp: List<Element> ->
//        createElement(name = "div", childrenProp = childrenProp, props = NoProps)
//    }
//
//    val noProps = createComponent { x: NoProps, y: NoProps ->
//        createElement("h1", "Text")
//    }

    @Test
    fun test() {

        val functionalComponent = createComponent { title: String ->
            h1.with(HProps(title))
        }

        val rootElement = createElement(functionalComponent, "Test", NoProps)
        val result = renderer.render(rootElement)
        //assertEquals("""{type: h1, props: {title: "Test"}}""", result)
    }

    @Test
    fun test4() {
        val context = createContext<String>()
        val provider = context.provider
        val consumer = context.consumer
        provider.with("Value from context") {
            consumer.with {
                { title: String ->
                    h1.with(HProps(title))
                }
            }
        }
    }

    @Test
    fun test5() {
        val el = "compoonents/text".with(props {
            "prop1" to "Value1"
        }) {
            +"components/test2".with(props {
                "prop2" to "Value 2"
                "listProp" to list {
                    +"list element 1"
                    +props {
                        "pr1" to "pr2"
                        "pr2" to list {
                            +"text 1"
                            +"text 2"
                            +"text 3"
                            +"components/test4".with(props {
                                "pr1" to "val 1"
                            }) {
                                +"Child"
                            }
                        }
                    }
                }
            }) {
                +"components/test3".with(props {
                    "prop4" to "value 4"
                    "prop5" to "Value 5"
                })
            }
        }
        val propsObj = props {
            "prop1" to "Value1 1"
            "prop2" to "Value 2"
            "prop3" to true
            "prop4" to props {
                "subprop1" to true
                "subprop2" to 4
                "subprop3" to list {
                    +("text" to "text")
                    +list {
                        (1..5).forEach { +("prop$it" to "$it") }
                    }

                }
            }
        }
        System.out.println(propsObj)
    }
}