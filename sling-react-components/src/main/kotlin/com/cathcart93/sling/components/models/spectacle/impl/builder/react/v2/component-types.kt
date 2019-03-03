package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

interface Component<T> {
    fun render(props: T, children: List<ElementDescriptor>): ElementDescriptor
}

//interface ComponentWithoutProps : Component {
//    fun render(): ElementDescriptor
//}

//interface ComponentWithProps<T> : Component {
//    fun render(props: T): ElementDescriptor
//}

//interface ComponentWithoutProps : Component {
//    fun render(children: List<ElementDescriptor>): ElementDescriptor
//}

//interface ComponentWithProps<T> : Component {
//    fun render(props: T, children: List<ElementDescriptor>): ElementDescriptor
//}