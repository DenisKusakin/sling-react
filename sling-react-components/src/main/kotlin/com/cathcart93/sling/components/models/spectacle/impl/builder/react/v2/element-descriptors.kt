package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

class FunctionalElementWithPropsAndChildrenDescriptor<T>(
        val component: Component<T>,
        val props: T,
        override val children: List<ElementDescriptor>) : ElementDescriptor {
    override var parent: ElementDescriptor? = null

    override fun render(): AtomElementDescriptor {
        val newElement = component.render(props, children)
        newElement.parent = this.parent
        newElement.children.forEach { it.parent = newElement }
        return newElement.render()
    }
}

//class FunctionalElementWithoutProps(
//        val component: ComponentWithoutProps,
//        override val children: List<ElementDescriptor>
//) : ElementDescriptor {
//    override var parent: ElementDescriptor? = null
//
//    override fun render(): AtomElementDescriptor {
//        val newElement = component.render(children)
//        newElement.parent = this.parent
//        newElement.children.forEach { it.parent = newElement }
//        return newElement.render()
//    }
//}

class AtomElementDescriptor(
        val name: String,
        val props: Any? = null,
        override val children: List<ElementDescriptor>
) : ElementDescriptor {
    override var parent: ElementDescriptor? = null

    override fun render(): AtomElementDescriptor {
        val newChildren = ArrayList<ElementDescriptor>()
        val newElement = AtomElementDescriptor(name, props, newChildren)
        newChildren.addAll(children.map {
            it.parent = newElement
            val newChild = it.render()
            newChild.parent = newElement
            return newChild
        })
        newElement.parent = this.parent
        return newElement
    }
}

class ContextProviderElementDescriptor<T>(
        val context: Context<T>,
        val value: T,
        override val children: List<ElementDescriptor>
) : ElementDescriptor {
    override var parent: ElementDescriptor? = null
    override fun render(): AtomElementDescriptor {
        if (children.size != 1) throw java.lang.IllegalStateException("Only single child allowed in context provider")
        val child = children[0]
        val newChild = child.render()
        newChild.parent = this.parent
        return newChild
    }
}

class ContextConsumerElementDescriptor<T>(
        val context: Context<T>,
        val renderer: (T) -> ElementDescriptor,
        override val children: List<ElementDescriptor>
) : ElementDescriptor {
    override var parent: ElementDescriptor? = null

    override fun render(): AtomElementDescriptor {
        val contextProviderElement = findContextProvider(this, context)
        val newElement = renderer(contextProviderElement.value)

        newElement.parent = this.parent
        return newElement.render()
    }

    private fun <T> findContextProvider(element: ElementDescriptor, context: Context<T>): ContextProviderElementDescriptor<T> {
        if (element is ContextProviderElementDescriptor<*> && element.context == context) {
            return element as ContextProviderElementDescriptor<T>
        }
        val parent = element.parent ?: throw IllegalStateException("Context is not provided")
        return findContextProvider(parent, context)
    }
}

/**
 * Primitive properties descriptors
 */
interface PropertyDescriptor

class StringPropertyDescriptor(val value: String) : PropertyDescriptor
class NumberPropertyDescriptor(val value: Number) : PropertyDescriptor
class BooleanPropertyDescriptor(val value: Boolean) : PropertyDescriptor
class ObjectPropertyDescriptor(val value: Map<String, PropertyDescriptor>) : PropertyDescriptor