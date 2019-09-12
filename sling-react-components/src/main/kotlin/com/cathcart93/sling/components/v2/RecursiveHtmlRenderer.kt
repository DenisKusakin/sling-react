package com.cathcart93.sling.components.v2

class RecursiveHtmlRenderer {
    fun render(element: Element): String {
        return render(element, emptyList())
    }

    private fun render(element: Element, contextProviders: List<ContextProviderElement<*>>): String {
        return when (element) {
            is BasicElement -> renderBasicElement(element, contextProviders)
            is FunctionalElement<*> -> renderFunctionalElement(element, contextProviders)
            is ContextConsumerElement<*> -> renderContextConsumerElement(element, contextProviders)
            is ContextProviderElement<*> -> renderContextProviderElement(element, contextProviders)
        }
    }

    private fun <T> renderContextProviderElement(
            element: ContextProviderElement<T>,
            contextProviders: List<ContextProviderElement<*>>
    ): String {
        return render(element.children, contextProviders + element)
    }

    private fun <T> renderContextConsumerElement(
            element: ContextConsumerElement<T>,
            contextProviders: List<ContextProviderElement<*>>
    ): String {
        val contextProvider = (contextProviders.asReversed()
                .find { it.context!!.javaClass == element.contextType }
                ?: throw IllegalArgumentException("Context is not provided for ${element.contextType}"))
                as ContextProviderElement<T>
        return render(element.consumer(contextProvider.context), contextProviders)
    }

    private fun renderBasicElement(
            element: BasicElement,
            contextProviders: List<ContextProviderElement<*>>
    ): String {
        val type = element.type
        val propsString = attributesString(element.props)
        val children = element.props.value["children"]
        return if (children == null) {
            if (propsString.isNotEmpty()) "<$type $propsString></$type>" else "<$type></$type>"
        } else {
            if (propsString.isNotEmpty()) "<$type $propsString>${renderChildren(children, contextProviders)}</$type>"
            else "<$type>${renderChildren(children, contextProviders)}</$type>"
        }
    }

    private fun renderChildren(
            prop: PrimitiveProp,
            contextProviders: List<ContextProviderElement<*>>
    ): String {
        return when (prop) {
            is StringProp -> "${prop.value}"
            is NumberProp -> "${prop.value}"
            is ElementProp -> render(prop.value, contextProviders)
            is ArrayProp -> prop.value.joinToString("") { renderChildren(it, contextProviders) }
            else -> TODO("Not Implemented")
        }
    }

    private fun attributesString(props: ObjectProp): String {
        return props.value
                .filter { it.key != "children" }
                .map { entry ->
                    val propValue = entry.value
                    val propValueString = when (propValue) {
                        is StringProp -> "\"${propValue.value}\""
                        is NumberProp -> "\"${propValue.value}\""
                        else -> "incorrect"
                    }
                    "${entry.key}=$propValueString"
                }.joinToString(" ")
    }

    private fun <T> renderFunctionalElement(
            element: FunctionalElement<T>,
            contextProviders: List<ContextProviderElement<*>>
    ): String {
        return render(element.component.render(element.props), contextProviders)
    }

}