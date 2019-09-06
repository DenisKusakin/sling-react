package com.cathcart93.sling.components.v2

import com.cathcart93.sling.components.models.spectacle.api.ResourceTypesConstants
import org.apache.sling.api.resource.Resource

object ResourceDeck : ComponentV2<Resource> {
    override fun render(props: Resource): Element {
        return RootComponentV2 {
            RootComponentProps(
                    content = Deck {
                        with(props.valueMap) {
                            asBoolean("autoplay")?.let { autoplay = it }
                            asInt("autoplayDuration")?.let { autoplayDuration = it }
                            asBoolean("autoplayLoop")?.let { autoplayLoop = it }
                            asBoolean("autoplayOnStart")?.let { autoplayOnStart = it }
                            asBoolean("controls")?.let { controls = it }
                            asInt("contentHeight")?.let { contentHeight = it }
                            asInt("contentWidth")?.let { contentWidth = it }
                            asBoolean("disableKeyboardControls")?.let { disableKeyboardControls = it }
                            asString("progress")?.let { progress = it }
                            asBoolean("showFullscreenControl")?.let { showFullscreenControl = it }
                            asInt("transitionDuration")?.let { transitionDuration = it }
                        }
                        slides = listOf(TestSlide { null }) + props.children.map { ResourceSlide { it } }
                    },
                    url = "test"
            )
        }
    }
}

object TestSlide : ComponentV2<String?> {
    override fun render(props: String?): Element {
        return Slide {
            components = listOf(
                    Text {
                        text = "Test Slide"
                    },
                    BlockQuote {
                        quote {
                            textColor = "green"
                            text = "Quote"
                        }
                        cite {
                            text = "Cite"
                            textColor = "blue"
                        }
                    }
            )
        }
    }

}

object ResourceSlide : ComponentV2<Resource> {
    override fun render(props: Resource): Element {
        return Slide {
            initBaseProps(props, this)
            with(props.valueMap) {
                asString("align")?.let { align = it }
                asString("controlColor")?.let { controlColor = it }
                asInt("goTo")?.let { goTo = it }
                asString("id")?.let { id = it }
                asInt("maxHeight")?.let { maxHeight = it }
                asInt("maxWidth")?.let { maxWidth = it }
                asString("notes")?.let { notes = it }
                asString("state")?.let { state = it }
                asInt("transitionDuration")?.let { transitionDuration = it }
                asInt("transitionDuration")?.let { transitionDuration = it }
            }
            components = props.children.map {
                AbstractResourceComponent { it }
            }
        }
    }
}

object ResourceText : ComponentV2<Resource> {
    override fun render(props: Resource): Element {
        return Text {
            initBaseProps(props, this)
            with(props.valueMap) {
                asBoolean("fit")?.let { fit = it }
                asInt("lineHeight")?.let { lineHeight = it }
                asString("text")?.let { text = it }
            }
        }
    }

}

object AbstractResourceComponent : ComponentV2<Resource> {
    override fun render(props: Resource): Element {
        return when (props.valueMap.asString("sling:resourceType")) {
            ResourceTypesConstants.TEXT -> ResourceText {
                props
            }
            else -> UnknownComponent {
                props
            }
        }
    }
}

object UnknownComponent : ComponentV2<Resource> {
    override fun render(props: Resource): Element {
        return Text {
            text = "Unknown component: ${props.path}"
            textColor = "red"
        }
    }
}

fun initBaseProps(resource: Resource, baseProps: BaseProps) {
    with(resource.valueMap) {
        asBoolean("italic")?.let { baseProps.italic = it }
        asBoolean("bold")?.let { baseProps.bold = it }
        asBoolean("caps")?.let { baseProps.caps = it }
        asInt("margin")?.let { baseProps.margin = it }
        asInt("padding")?.let { baseProps.padding = it }
        asString("textColor")?.let { baseProps.textColor = it }
        asInt("textSize")?.let { baseProps.textSize = it }
        asString("bgColor")?.let { baseProps.bgColor = it }
        asString("bgGradient")?.let { baseProps.bgGradient = it }
        asInt("height")?.let { baseProps.height = it }
    }
}