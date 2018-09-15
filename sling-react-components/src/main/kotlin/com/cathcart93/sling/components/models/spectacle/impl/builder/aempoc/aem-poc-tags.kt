package com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.*

interface ReactTag {
    fun toReactElement(): ReactElement
}

class Heading(private val text: String) : ReactTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(
                name = "Heading",
                props = mapOf("heading" to text.toReactProp())
        )
    }
}

class HeadingWithButtonButton(private val title: String) : ReactTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(
                name = "heading-with-button/button",
                props = mapOf("title" to title.toReactProp())
        )
    }
}

class HeadingWithButton(private val text: String, private val button: ReactProp) : ReactTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(
                name = "HeadingWithButton",
                props = mapOf("heading" to text.toReactProp(), "button" to button)
        )
    }
}

class Parsys(private val children: List<ReactElement>) : ReactTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(
                name = "Parsys",
                children = children
        )
    }

}

data class Link(val href: String, val title: String)

class Navigation(private val links: List<Link>) : ReactTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(
                name = "Navigation",
                props = mapOf(
                        "links" to ArrayProp(
                                links.map {
                                    ObjectProps(mapOf(
                                            "link" to it.href.toReactProp(),
                                            "title" to it.title.toReactProp()
                                    ))
                                }
                        )
                )
        )
    }

}

class AuthorWrapper(private val url: String) : ReactTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(
                name = "AuthorWrapper",
                props = mapOf("url" to url.toReactProp())
        )
    }
}

class AuthorWrapperV2(private val id: String) : ReactTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(
                name = "AuthorWrapperV2",
                props = mapOf("id" to id.toReactProp())
        )
    }
}

class Page(private val navigation: ReactElement, private val content: ReactElement) : ReactTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(
                name = "Page",
                props = mapOf(
                        "navigation" to navigation,
                        "content" to content
                )
        )
    }
}

data class ImageGalleryItem(val original: String, val thumbnail: String)

class ImageGallery(private val slides: List<ImageGalleryItem>) : ReactTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(
                name = "ImageGallery",
                props = mapOf("images" to ArrayProp(
                        slides.map {
                            ObjectProps(
                                    mapOf(
                                            "original" to it.original.toReactProp(),
                                            "thumbnail" to it.thumbnail.toReactProp()
                                    )
                            )
                        })
                )
        )
    }
}