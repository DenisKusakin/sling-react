package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.AtomElementDescriptor
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.ElementDescriptor
import com.google.gson.Gson

class ReactJsonRenderer : ElementConsumer<String> {
    override fun render(element: ElementDescriptor): String {
//        val resultElement = element.render()
//        System.out.println(renderAtomElement(resultElement))

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

//    private fun elemToJson(type: String, ) : String{
//
//    }

    data class RenderResult(val type: String, val props: Any?, val children: List<RenderResult>)

    private fun elemToRenderResult(elem: AtomElementDescriptor): RenderResult {
        return RenderResult(elem.name, elem.props, elem.children.map { elemToRenderResult(it as AtomElementDescriptor) })
    }

    private fun renderAtomElement(elem: AtomElementDescriptor): String {
        val renderResult = elemToRenderResult(elem)
        val gson = Gson()
        return gson.toJson(renderResult)
    }
}