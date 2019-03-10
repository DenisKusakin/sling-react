package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.render

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.*

class ReactRenderer : ElementConsumer {
    override fun render(element: Element): Element {
        return render(element, emptyList())
    }

    private fun render(element: Element, contextProviders: List<ContextProviderElement<*>>): Element {
        return when (element) {
            is FunctionalElement<*, *> -> renderFunctionalElement(element, contextProviders)
            is BasicElement -> renderBasicElement(element, contextProviders)
            is ContextProviderElement<*> -> renderContextProviderElement(element, contextProviders)
            is ContextConsumerElement<*> -> renderContextConsumerElement(element, contextProviders)
            is TextElement -> element.copy()
        }
    }

    private fun <T, U> renderFunctionalElement(
            element: FunctionalElement<T, U>,
            contextProviders: List<ContextProviderElement<*>>
    ): Element {
        return render(element.component.render(element.props, element.children), contextProviders)
    }

    private fun renderBasicElement(
            element: BasicElement,
            contextProviders: List<ContextProviderElement<*>>
    ): Element {
        return element.copy(
                children = element.children.map { render(it, contextProviders) }
        )
    }

    private fun <T> renderContextProviderElement(
            element: ContextProviderElement<T>,
            contextProviders: List<ContextProviderElement<*>>
    ): Element {
        return render(element.children, listOf(*contextProviders.toTypedArray(), element))
    }

    private fun <T> renderContextConsumerElement(
            element: ContextConsumerElement<T>,
            contextProviders: List<ContextProviderElement<*>>
    ): Element {
        val contextProvider = contextProviders
                .reversed()
                .find { it.context == element.context }
                ?.let { it as ContextProviderElement<T> }
                ?: throw IllegalStateException("Context provider not found for ${element.context.javaClass.name}")
        return render(element.children(contextProvider.value), contextProviders)
    }
}