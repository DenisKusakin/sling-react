package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.AtomElementDescriptor
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.ElementDescriptor
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.FunctionalElementWithPropsAndChildrenDescriptor

class ReactJsonRenderer : ElementConsumer<String> {
    override fun render(element: ElementDescriptor): String {
        val resultElement = element.render()
        System.out.println(renderAtomElement(resultElement))

//        when (element) {
//            is AtomElementDescriptor -> """
//                {
//                    type: "${element.name}",
//                    props: "Test",
//                    children: "[${element.children.joinToString(",") { this.render(it) }}]"
//                }
//            """.trimIndent()
//            is FunctionalElementWithPropsAndChildrenDescriptor<*> -> {
//                val renderResult = element.render()
//                """
//
//                """.trimIndent()
//            }
//        }
        return ""
    }

    private fun renderAtomElement(elem: AtomElementDescriptor): String {
        return """{
                    type: ${elem.name},
                    children: [${elem.children.joinToString(",") { renderAtomElement(it as AtomElementDescriptor) }}]
                }
        """.trimIndent()
    }
}