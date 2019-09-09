package com.cathcart93.sling.components.v2

import com.cathcart93.sling.components.models.spectacle.api.ResourceTypesConstants
import org.apache.sling.api.resource.Resource

object ResourceDeck : Component<Resource> {
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
                    url = props.path
            )
        }
    }
}

object TestSlide : Component<String?> {
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

object ResourceSlide : Component<Resource> {
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
            components = listOf(
                    "Container" {
                        "resourcePath" to "${props.path}/"
                        "components" to ArrayProp(emptyList())
                        "moveInfo" to {

                        }
                        "children" to props.children.map {
                            AbstractResourceComponent { it }
                        }.map { ElementProp(it) }
                        "components" list {
                            item {
                                "title" to "Text"
                                "description" to "Text component"
                                "props" to {
                                    ":nameHint" to "spectacle/components/Text"
                                    ":order" to "last"
                                    "sling:resourceType" to "spectacle/components/Text"
                                }
                            }
                            item {
                                "title" to "Heading"
                                "description" to "Heading component"
                                "props" to {
                                    ":nameHint" to "spectacle/components/Heading"
                                    ":order" to "last"
                                    "sling:resourceType" to "spectacle/components/Heading"
                                }
                            }
                        }
                    }
            )
        }
    }
}

object ResourceText : Component<Resource> {
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

object ResourceHeading : Component<Resource> {
    override fun render(props: Resource): Element {
        return Heading {
            initBaseProps(props, this)
            with(props.valueMap) {
                asString("text")?.let { text = it }
                asInt("size")?.let { this@Heading.size = it }
            }
        }
    }

}

/**
 * Dialogs
 */

val EditableText = ResourceText.editableComponent { resource: Resource ->
    text("text") {
        value = resource.valueMap.asString("text")
        title = "Text"
    }
    basicDialog(this, resource)
}

val EditableHeading = ResourceHeading.editableComponent { resource: Resource ->
    text("text") {
        value = resource.valueMap.asString("text")
        title = "Title"
    }
    select("size") {
        value = resource.valueMap.asString("size") ?: "1"
        title = "Size"
        options = (1..6).map { SelectOptions.Option(label = "H$it", value = "$it") }
    }
    basicDialog(this, resource)
}

fun basicDialog(dialog: Dialog, resource: Resource) {
    with(dialog) {
        checkbox("italic") {
            value = resource.valueMap.asBoolean("italic") ?: false
            title = "Italic"
        }
        checkbox("bold") {
            value = resource.valueMap.asBoolean("bold") ?: false
            title = "Bold"
        }
        checkbox("caps") {
            value = resource.valueMap.asBoolean("caps") ?: false
            title = "Caps"
        }
        text("margin") {
            value = resource.valueMap.asString("margin")
            title = "Margin"
        }
        text("padding") {
            value = resource.valueMap.asString("padding")
            title = "Padding"
        }
        color("textColor") {
            value = resource.valueMap.asString("textColor")
            title = "Text Color"
        }
        text("textSize") {
            value = resource.valueMap.asString("textSize")
            title = "Text Size"
        }
        color("bgColor") {
            value = resource.valueMap.asString("bgColor")
            title = "Background Color"
        }
        text("bgGradient") {
            value = resource.valueMap.asString("bgGradient")
            title = "Background Gradient"
        }
        text("height") {
            value = resource.valueMap.asString("height")
            title = "Height"
        }
    }
}

/**
 * END Dialogs
 */

object AbstractResourceComponent : Component<Resource> {
    override fun render(props: Resource): Element {
        return when (props.valueMap.asString("sling:resourceType")) {
            ResourceTypesConstants.TEXT -> EditableText
            ResourceTypesConstants.HEADING -> EditableHeading
            else -> UnknownComponent
        }{
            props
        }
    }
}

object UnknownComponent : Component<Resource> {
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