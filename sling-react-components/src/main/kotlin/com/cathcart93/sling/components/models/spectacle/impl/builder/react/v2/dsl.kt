import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.Component
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.ElementDescriptor
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.NoProps
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.createElement

class ComponentWithProps<T, U>(val component: Component<T, U>, val props: T) {
    operator fun invoke(childrenProducer: () -> U) : ElementDescriptor{
        return createElement(component, props, childrenProducer())
    }
}

fun <T, U> Component<T, U>.with(props: T, children: () -> U): ElementDescriptor {
    return createElement(this, props, children())//ComponentWithProps(this, props)
}

fun <T> Component<T, NoProps>.with(props: T) {

}

operator fun <T> Component<NoProps, T>.invoke(t: () -> Unit){

}

