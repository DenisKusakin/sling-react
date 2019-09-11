package com.cathcart93.sling.components.v2

class RecursiveHtmlRenderer {
    fun render(element: Element) : String {
        TODO("")
    }

//    private fun render(element: Element, contextProviders: List<ContextProviderElement<*>>): StringBuilder {
//        return when (element) {
//            is BasicElement -> renderBasicElement(element, contextProviders)
//            is FunctionalElement<*> -> renderFunctionalElement(element, contextProviders)
//            is ContextConsumerElement<*> -> renderContextConsumerElement(element, contextProviders)
//            is ContextProviderElement<*> -> renderContextProviderElement(element, contextProviders)
//        }
//    }
//
//    private fun <T> renderContextProviderElement(
//            element: ContextProviderElement<T>,
//            contextProviders: List<ContextProviderElement<*>>
//    ): StringBuilder {
//        return render(element.children, contextProviders + element)
//    }
//
//    private fun <T> renderContextConsumerElement(
//            element: ContextConsumerElement<T>,
//            contextProviders: List<ContextProviderElement<*>>
//    ): StringBuilder {
//        val contextProvider = (contextProviders.asReversed()
//                .find { it.context!!.javaClass == element.contextType }
//                ?: throw IllegalArgumentException("Context is not provided for ${element.contextType}"))
//                as ContextProviderElement<T>
//        return render(element.consumer(contextProvider.context), contextProviders)
//    }

//    private fun renderBasicElement(
//            element: BasicElement,
//            contextProviders: List<ContextProviderElement<*>>
//    ): StringBuilder {
//        val jsonObject = JsonObject()
//        jsonObject.add("__type", JsonPrimitive(element.type))
//        val props = renderPrimitive(element.props, contextProviders) as JsonObject
//        props.entrySet().forEach { jsonObject.add(it.key, it.value) }
//        //jsonObject.add("props", renderPrimitive(element.props, contextProviders))
//        return jsonObject
//    }
//
//    private fun <T> renderFunctionalElement(
//            element: FunctionalElement<T>,
//            contextProviders: List<ContextProviderElement<*>>
//    ): StringBuilder {
//        return render(element.component.render(element.props), contextProviders)
//    }
//
//    private fun renderPrimitive(prop: PrimitiveProp, contextProviders: List<ContextProviderElement<*>>): JsonElement {
//        return when (prop) {
//            is StringProp -> JsonPrimitive(prop.value)
//            is NumberProp -> JsonPrimitive(prop.value)
//            is BooleanProp -> JsonPrimitive(prop.value)
//            is ArrayProp -> {
//                val jsonArray = JsonArray()
//                prop.value.map { renderPrimitive(it, contextProviders) }.forEach { jsonArray.add(it) }
//                return jsonArray
//            }
//            is ObjectProp -> {
//                val jsonObject = JsonObject()
//                prop.value
//                        .forEach { (name, value) -> jsonObject.add(name, renderPrimitive(value, contextProviders)) }
//                return jsonObject
//            }
//            is ElementProp -> render(prop.value, contextProviders)
//        }
//    }
}