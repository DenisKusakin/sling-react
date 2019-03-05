import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.Component
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.ElementDescriptor
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.NoProps
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.createElement

class ComponentWithProps<T, U>(val component: Component<T, U>, val props: T) {
//    operator fun invoke(childrenProducer: () -> U) : ElementDescriptor{
//        return createElement(component, props, childrenProducer())
//    }
//    fun
}

fun <T, U> Component<T, U>.with(props: T, childrenBuilder: () -> U): ElementDescriptor {
    //val componentWithProps = ComponentWithProps(this, props)
    //childrenBuilder(componentWithProps)
    return createElement(this, props, childrenBuilder())//ComponentWithProps(this, props)
}

fun <T> Component<T, NoProps>.with(props: T) : ElementDescriptor{
 TODO("")
}

//operator fun <T> Component<NoProps, (T) -> ElementDescriptor>.invoke(block: () -> ElementDescriptor) : ElementDescriptor {
//    TODO("")
//}

operator fun <T> Component<NoProps, T>.invoke(t: () -> T) : ElementDescriptor{
 TODO("")
}

//operator fun <T> Component<NoProps, T>.invoke(props: T): ElementDescriptor {
//    TODO("")
//}