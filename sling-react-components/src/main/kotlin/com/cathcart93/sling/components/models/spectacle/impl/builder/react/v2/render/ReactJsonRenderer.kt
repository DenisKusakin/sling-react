package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.AtomElementDescriptor
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.ElementDescriptor
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.FunctionalElementDescriptor

class ReactJsonRenderer : ElementConsumer<String> {
    override fun render(element: ElementDescriptor): String {
        when (element) {
            is AtomElementDescriptor -> """
                {
                    type: "${element.name}",
                    props: "Test",
                    children: "[${element.children.joinToString(",") { this.render(it) }}]"
                }
            """.trimIndent()
            is FunctionalElementDescriptor<*> -> {
                val renderResult = element.render()
                """

                """.trimIndent()
            }
        }
        return ""
    }
}