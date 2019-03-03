package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2


object Comp1 : Component<String> {
    override fun render(props: String, children: List<ElementDescriptor>): ElementDescriptor {
        val testContext = createContext<String>()
        return createElement(
                testContext.provider, "context value",
                createElement(Comp2, "test")
        )
    }

}

object Comp2 : Component<String> {
    override fun render(props: String, children: List<ElementDescriptor>): ElementDescriptor {
        val testContext = createContext<String>()
        return createElement(
                testContext.consumer,
                { value: String ->
                    createElement(
                            Comp3, value,
                            createElement(Comp1, props))
                })
    }
}

object Comp3 : Component<String> {
    override fun render(props: String, children: List<ElementDescriptor>): ElementDescriptor {
        return createElement(comp4, "Test Title")
    }
}

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

val comp4: (String) -> ElementDescriptor = { title: String ->
    val props = ObjectPropertyDescriptor(mapOf("title" to StringPropertyDescriptor(title)))
    createElement("test", props)
}