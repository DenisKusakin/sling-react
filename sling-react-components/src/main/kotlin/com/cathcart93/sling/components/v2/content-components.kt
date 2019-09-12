package com.cathcart93.sling.components.v2

import com.cathcart93.sling.components.models.spectacle.api.ResourceTypesConstants
import org.apache.sling.api.resource.Resource

object Spectacle : Component<SpectacleProps> {
    override fun render(props: SpectacleProps): Element {
        val ImageContext = ImageSrcContext(buildUrl = { src ->
            src
        })

        return (props.resource.valueMap.asString("xml"))?.let {
            MeduzaSpectacle { it }
        } ?: withContext(ImageContext) {
            withContext(EditModeContext(!props.isPreviewMode), {
                DeckComponent {
                    props.resource
                }
            })
        }
    }

}

data class SpectacleProps(val isPreviewMode: Boolean, val resource: Resource)

object AuthorResourceDeck : Component<Resource> {
    override fun render(props: Resource): Element {
        return RootComponentV2 {
            RootComponentProps(
                    content = ResourceDeck { props },
                    url = props.path
            )
        }
    }
}

object ResourceDeck : Component<Resource> {
    override fun render(props: Resource): Element {
        return Deck {
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
            slides = props.children.map { SlideComponent { it } }
        }
    }
}

object DeckComponent : Component<Resource> {
    override fun render(props: Resource): Element {
        return isEditMode { isEditMode ->
            (if (isEditMode) AuthorResourceDeck else ResourceDeck) {
                props
            }
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
            components = props.children.map {
                AbstractResourceComponent { it }
            }
        }
    }
}

object SlideComponent : Component<Resource> {
    override fun render(props: Resource): Element {
        return isEditMode { isEditMode ->
            (if (isEditMode) EditableSlide else ResourceSlide) {
                props
            }
        }
    }

}

object EditableSlide : Component<Resource> {
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
                            item {
                                "title" to "Image"
                                "description" to "Image component"
                                "props" to {
                                    ":nameHint" to "image"
                                    ":order" to "last"
                                    "sling:resourceType" to ResourceTypesConstants.IMAGE
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
                asBoolean("fit")?.let { fit = it }
                asString("text")?.let { text = it }
                asInt("size")?.let { this@Heading.size = it }
                asInt("lineHeight")?.let { this@Heading.lineHeight = it }
            }
        }
    }

}

object ResourceImage : Component<Resource> {
    override fun render(props: Resource): Element {
        return consumeContext(ImageSrcContext::class.java) { imageSrcContext ->
            val (buildUrl) = imageSrcContext
            Image {
                initBaseProps(props, this)
                with(props.valueMap) {
                    asString("src")?.let { src = buildUrl(it) }
                    asString("alt")?.let { alt = it }
                }
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
    checkbox("fit") {
        value = resource.valueMap.asBoolean("fit") ?: false
        title = "Fit"
    }
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

val EditableImage = ResourceImage.editableComponent { resource: Resource ->
    text("src") {
        value = resource.valueMap.asString("src")
        title = "Source Url"
    }
    basicDialog(this, resource)
}

fun basicDialog(dialog: Dialog, resource: Resource) {
    with(dialog) {
        text("href") {
            value = resource.valueMap.asString("href")
            title = "Href"
        }
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
        return isEditMode { isEditMode ->
            when (props.valueMap.asString("sling:resourceType")) {
                ResourceTypesConstants.TEXT -> if (isEditMode) EditableText else ResourceText
                ResourceTypesConstants.HEADING -> if (isEditMode) EditableHeading else ResourceHeading
                ResourceTypesConstants.IMAGE -> if (isEditMode) EditableImage else ResourceImage
                else -> UnknownComponent
            }{
                props
            }.let { element ->
                props.valueMap.asString("href")?.let {
                    Link {
                        href = it
                        content = element
                    }
                } ?: element
            }
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