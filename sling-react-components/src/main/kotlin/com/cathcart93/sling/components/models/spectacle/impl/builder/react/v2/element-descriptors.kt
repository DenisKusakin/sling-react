package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

//interface ElementDescriptor {
//
//}

class FunctionalElementDescriptor<T>(
        val component: Component<T>,
        val props: T,
        override val children: List<ElementDescriptor>) : ElementDescriptor {
    override var parent: ElementDescriptor? = null

    fun render(): ElementDescriptor {
        return component.render(props)
    }
}

class AtomElementDescriptor(
        val name: String,
        val props: ObjectPropertyDescriptor = ObjectPropertyDescriptor(emptyMap()),
        override val children: List<ElementDescriptor>
) : ElementDescriptor {
    override var parent: ElementDescriptor? = null
}

class ContextProviderElementDescriptor<T>(
        val context: Context<T>,
        val value: T,
        override val children: List<ElementDescriptor>
) : ElementDescriptor {
    override var parent: ElementDescriptor? = null
}

class ContextConsumerElementDescriptor<T>(
        val context: Context<T>,
        val renderer: (T) -> ElementDescriptor,
        override val children: List<ElementDescriptor>
) : ElementDescriptor {
    override var parent: ElementDescriptor? = null
}

/**
 * Primitive properties descriptors
 */
interface PropertyDescriptor

class StringPropertyDescriptor(val value: String) : PropertyDescriptor
class NumberPropertyDescriptor(val value: Number) : PropertyDescriptor
class BooleanPropertyDescriptor(val value: Boolean) : PropertyDescriptor
class ObjectPropertyDescriptor(val value: Map<String, PropertyDescriptor>) : PropertyDescriptor