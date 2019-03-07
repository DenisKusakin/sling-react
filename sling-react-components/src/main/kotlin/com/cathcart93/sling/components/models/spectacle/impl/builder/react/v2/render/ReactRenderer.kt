package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.BasicElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.Element
import com.google.gson.Gson

class ReactRenderer : ElementConsumer {
    override fun render(element: Element): Element {
        return element
//        val resultElement = element.render()
//        System.out.println(renderAtomElement(resultElement))

//        when (element) {
//            is BasicElement -> """
//                {
//                    type: "${element.name}",
//                    props: "Test",
//                    children: "[${element.children.joinToString(",") { this.render(it) }}]"
//                }
//            """.trimIndent()
//            is FunctionalElement<*> -> {
//                val renderResult = element.render()
//                """
//
//                """.trimIndent()
//            }
//        }
//        return TODO("")
    }

//    private fun elemToJson(type: String, ) : String{
//
//    }

    data class RenderResult(val type: String, val props: Any?, val children: List<RenderResult>)

    private fun elemToRenderResult(elem: BasicElement): RenderResult {
        return RenderResult(elem.type, elem.props, elem.children.map { elemToRenderResult(it as BasicElement) })
    }

    private fun renderAtomElement(elem: BasicElement): String {
        val renderResult = elemToRenderResult(elem)
        val gson = Gson()
        return gson.toJson(renderResult)
    }
}