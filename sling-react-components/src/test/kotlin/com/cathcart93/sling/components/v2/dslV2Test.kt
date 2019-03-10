package com.cathcart93.sling.components.v2

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.Component
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.Element
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.createComponent
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.invoke
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render.ReactRenderer
import org.junit.Test

open class TestCases {
    @Test
    fun test() {
        val component: Component<String, List<Element>> = createComponent { title: String, content: List<Element> ->
            container {
                h1(HProps(title))
                container {
                    content.forEach { +it }
                }
            }
        }

        val componentWithoutProps = createComponent {
            h2(HProps("Test"))
        }
        val element = container {
            h1(HProps("Test"))
            h1(HProps("Test 2"))
            h1(HProps("Test 3"))

            container {
                h1(HProps("Sub Test"))
                h1(HProps("Sub Test 2"))

                container {
                    h2(HProps("Test Test Test"))
                }
            }

            component<String>("Component Title") {
                h2(HProps("!!!"))
                componentWithoutProps()
            }

            componentWithoutProps()
        }

        val renderer = ReactRenderer()

        renderer.render(element)
    }
}