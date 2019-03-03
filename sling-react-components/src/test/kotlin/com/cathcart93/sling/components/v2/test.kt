package com.cathcart93.sling.components.v2

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.ElementDescriptor
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.ObjectPropertyDescriptor
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.StringPropertyDescriptor
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.createElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render.ReactJsonRenderer
import org.junit.Test
import kotlin.test.assertEquals

open class Test {
    @Test
    fun test() {
        val comp: (String) -> ElementDescriptor = {
            val props = ObjectPropertyDescriptor(mapOf("title" to StringPropertyDescriptor(it)))
            createElement("h1", props)
        }

        val functionalComponent: (String) -> ElementDescriptor = {
            createElement(comp, it)
        }

        val renderer = ReactJsonRenderer()
        val rootElement = createElement(functionalComponent, "Test")
        val result = renderer.render(rootElement)
        assertEquals("""{type: h1, props: {title: "Test"}}""", result)
    }
}