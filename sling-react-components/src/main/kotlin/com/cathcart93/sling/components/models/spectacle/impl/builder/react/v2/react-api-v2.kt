package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2


object Comp1 : Component<String, List<ElementDescriptor>> {
    override fun render(props: String, children: List<ElementDescriptor>): ElementDescriptor {
        val testContext = createContext<String>()
        return createElement(
                testContext.provider, "context value",
                createElement(Comp2, "test", emptyList())
        )
    }

}

object Comp2 : Component<String, List<ElementDescriptor>> {
    override fun render(props: String, children: List<ElementDescriptor>): ElementDescriptor {
        val testContext = createContext<String>()
        return createElement(
                testContext.consumer,
                NoProps,
                { value: String ->
                    createElement(
                            comp3,
                            value, NoProps)
                })
    }
}

val comp3 = createComponent { title: String ->
    AtomElementDescriptor("h1", title, emptyList())
}

//object Comp3 : Component<String, List<ElementDescriptor>> {
//    override fun render(props: String, children: List<ElementDescriptor>): ElementDescriptor {
//        return //createElement(comp4, "Test Title")
//    }
//}

//TODO: Implement
//object Comp4 : ComponentWithProps<String> {
//    override fun render(props: String, children: List<ElementDescriptor>): ElementDescriptor {
//        return el(Comp3, "test") {
//            elc(Comp3, "test") {
//
//            }
//        }
//    }
//}

//val comp4: (String) -> ElementDescriptor = { title: String ->
//    val props = ObjectPropertyDescriptor(mapOf("title" to StringPropertyDescriptor(title)))
//    createElement("test", props)
//}