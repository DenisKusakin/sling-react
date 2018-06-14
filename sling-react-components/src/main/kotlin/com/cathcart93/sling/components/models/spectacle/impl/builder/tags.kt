package com.cathcart93.sling.components.models.spectacle.impl.builder

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.*

@DslMarker
annotation class TagMarker

interface SpectacleTag {
    fun toReactElement(): ReactElement
}

//@TagMarker
class Deck(val theme: ExtendedDefaultTheme) : SpectacleTag {
    override fun toReactElement(): ReactElement {
        val colors = mutableMapOf(
                "primary" to theme.primaryColor?.toReactProp(),
                "secondary" to theme.secondaryColor?.toReactProp(),
                "tertiary" to theme.tertiaryColor?.toReactProp(),
                "quarternary" to theme.quarternaryColor?.toReactProp()
        ).filter { it.value != null }.map { it.key to it.value!! }.toMap()
        val fonts = mutableMapOf(
                "primary" to theme.primaryFont?.toReactProp(),
                "secondary" to theme.secondaryFont?.toReactProp()
        ).filter { it.value != null }.map { it.key to it.value!! }.toMap()
        val props = mapOf(
                "transition" to transition?.toReactProp(),
                "transitionDuration" to transitionDuration?.toReactProp(),
                "controls" to controls?.toReactProp(),
                "progress" to progress?.toReactProp(),
                "colors" to ObjectProps(colors),
                "fonts" to ObjectProps(fonts)
        ).filter { it.value != null }.map { it.key to it.value!! }.toMap()

        return ReactElement(name = "Deck", children = slides.map { it.toReactElement() }, props = props)
    }

    val slides: MutableList<Slide> = mutableListOf()
    var transition: SlideTransition? = null
    var transitionDuration: Int? = null
    var progress: Progress? = null
    var controls: Boolean? = null

//    var primaryColor: String? = null
//    var secondaryColor: String? = null
//    var tertiaryColor: String? = null
//    var quarternaryColor: String? = null
//    var primaryFont: String? = null
//    var secondaryFont: String? = null
}

open class ExtendedDefaultTheme {
    open var primaryColor: String? = null
    open var secondaryColor: String? = null
    open var tertiaryColor: String? = null
    open var quarternaryColor: String? = null
    open var primaryFont: String? = null
    open var secondaryFont: String? = null
}

abstract class BaseTag {
    var italic: Boolean? = null
    var bold: Boolean? = null
    var caps: Boolean? = null
    var margin: Int? = null
    var padding: Int? = null
    var textColor: TextColor? = null
    var textSize: String? = null
    var textAlign: TextAlign? = null
    var textFont: String? = null
    var bgColor: String? = null

    fun baseProps(): Map<String, ReactProp> {
        return mapOf(
                "italic" to italic?.toReactProp(),
                "bold" to bold?.toReactProp(),
                "caps" to caps?.toReactProp(),
                "margin" to margin?.toReactProp(),
                "padding" to padding?.toReactProp(),
                "textColor" to textColor?.toReactProp(),
                "textSize" to textSize?.toReactProp(),
                "textAlign" to textAlign?.toReactProp(),
                "textFont" to textFont?.toReactProp(),
                "bgColor" to bgColor?.toReactProp()
        ).filter { it.value != null }.map { it.key to it.value!! }.toMap()
    }
}

//@TagMarker
class Slide : SpectacleTag, BaseTag() {
    override fun toReactElement(): ReactElement {
        val props = mutableMapOf(
                "align" to align?.toReactProp(),
                "transition" to transition?.toReactProp(),
                "transitionDuration" to transitionDuration?.toReactProp(),
                "notes" to notes?.toReactProp(),
                "id" to id?.toReactProp()
        )
        props.putAll(baseProps())
        return ReactElement(
                name = "Slide",
                children = children.map { it.toReactElement() },
                props = props.filter { it.value != null }.map { it.key to it.value!! }.toMap()
        )
    }

    val children: MutableList<SpectacleTag> = mutableListOf()
    var align: SlideAlign? = null
    var transition: SlideTransition? = null
    var transitionDuration: Int? = null
    var notes: String? = null
    var id: String? = null
}

class Heading(private val text: String) : SpectacleTag, BaseTag() {
    override fun toReactElement(): ReactElement {
        val props = mutableMapOf(
                "fit" to fit?.toReactProp(),
                "lineHeight" to lineHeight?.toReactProp(),
                "size" to size?.toReactProp()
        )
        props.putAll(baseProps())
        return ReactElement(
                name = "Heading",
                children = StringProp(text), props = props.filter { it.value != null }.map { it.key to it.value!! }.toMap()
        )
    }

    var fit: Boolean? = null
    var lineHeight: Int? = null
    var size: Int? = null

}

class BlockQuote : BaseTag(), SpectacleTag {
    override fun toReactElement(): ReactElement {
        val children: List<SpectacleTag> = listOfNotNull(quote, cite)
        return ReactElement(name = "BlockQuote", children = children.map { it.toReactElement() })
    }

    var quote: Quote? = null
    var cite: Cite? = null
}

class Quote(val quote: String) : BaseTag(), SpectacleTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(name = "Quote", children = quote.toReactProp(), props = baseProps())
    }
}

class Cite(val cite: String) : BaseTag(), SpectacleTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(name = "Cite", children = cite.toReactProp(), props = baseProps())
    }
}

class Image(val src: String) : SpectacleTag, BaseTag() {
    override fun toReactElement(): ReactElement {
        val ownProps = mutableMapOf(
                "src" to src.toReactProp(),
                "height" to height?.toReactProp(),
                "width" to width?.toReactProp()
        )
        ownProps.putAll(baseProps())
        val props = ownProps.filter { it.value != null }.map { it.key to it.value!! }.toMap()
        return ReactElement(name = "Image", props = props)
    }

    var height: String? = null
    var width: String? = null

}

class Link(val href: String, val text: String) : SpectacleTag, BaseTag() {
    override fun toReactElement(): ReactElement {
        val ownProps = mutableMapOf(
                "href" to href.toReactProp(),
                "target" to target?.toReactProp()
        )
        ownProps.putAll(baseProps())
        val props = ownProps.filter { it.value != null }.map { it.key to it.value!! }.toMap()
        return ReactElement(name = "Link", props = props, children = text.toReactProp())
    }

    var target: LinkTarget? = null
}

class Text(val text: String) : SpectacleTag, BaseTag() {
    override fun toReactElement(): ReactElement {
        val ownProps = mutableMapOf(
                "fit" to fit?.toReactProp(),
                "lineHeight" to lineHeight?.toReactProp()
        )
        ownProps.putAll(baseProps())
        val props = ownProps.filter { it.value != null }.map { it.key to it.value!! }.toMap()
        return ReactElement(name = "Text", props = props, children = text.toReactProp())
    }

    var lineHeight: Int? = null
    var fit: Boolean? = null
}

class Appear(val child: SpectacleTag) : SpectacleTag {
    override fun toReactElement(): ReactElement {
        val props = mapOf(
                "transitionDuration" to transitionDuration?.toReactProp()
        ).filter { it.value != null }.map { it.key to it.value!! }.toMap()
        return ReactElement(name = "Appear", props = props, children = child.toReactElement())
    }

    var transitionDuration: Int? = null
}

class Code(val source: String, val lang: String) : SpectacleTag, BaseTag() {
    override fun toReactElement(): ReactElement {
        return ReactElement(name = "CodePane", props = mapOf(
                "source" to source.toReactProp(),
                "lang" to lang.toReactProp()
        ))
    }
}

class Markdown(val source: String) : SpectacleTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(name = "Markdown", props = mapOf(
                "source" to source.toReactProp()
        ))
    }
}

//@TagMarker
fun Deck.slide(block: Slide.() -> Unit) {
    val slide = Slide()
    block(slide)
    this.slides.add(slide)
}

fun Deck.slide(slide: Slide) {
    this.slides.add(slide)
}

//@TagMarker
fun Slide.heading(text: String, block: Heading.() -> Unit) {
    val heading = Heading(text)
    block(heading)
    this.children.add(heading)
}

fun Slide.blockQuote(block: BlockQuote.() -> Unit) {
    val blockQuote = BlockQuote()
    block(blockQuote)
    this.children.add(blockQuote)
}

fun Slide.image(src: String, block: Image.() -> Unit) {
    val image = Image(src)
    block(image)
    this.children.add(image)
}

fun Slide.link(href: String, text: String, block: Link.() -> Unit) {
    val link = Link(href, text)
    block(link)
    this.children.add(link)
}

fun Slide.appear(child: SpectacleTag, block: Appear.() -> Unit) {
    val appear = Appear(child)
    block(appear)
    this.children.add(appear)
}

fun Slide.text(text: String, block: Text.() -> Unit) {
    val textTag = Text(text)
    block(textTag)
    this.children.add(textTag)
}

fun Slide.comp(child: SpectacleTag) {
    this.children.add(child)
}

fun Slide.code(source: String, lang: String) {
    this.children.add(Code(source = source, lang = lang))
}

fun Slide.markdown(source: String) {
    this.children.add(Markdown(source))
}

fun BlockQuote.quote(text: String, block: Quote.() -> Unit) {
    val quote = Quote(text)
    block(quote)
    this.quote = quote
}

fun BlockQuote.cite(text: String, block: Cite.() -> Unit) {
    val cite = Cite(text)
    block(cite)
    this.cite = cite
}

fun SpectacleTag.appear(shouldAppear: Boolean = true): SpectacleTag {
    return if (shouldAppear) Appear(this) else this
}

/**
 *
 */
//@TagMarker
fun deck(theme: ExtendedDefaultTheme, block: Deck.() -> Unit): Deck {
    val deck = Deck(theme)
    block(deck)
    return deck
}

fun slide(block: Slide.() -> Unit): Slide {
    val slide = Slide()
    block(slide)
    return slide
}

fun heading(text: String, block: Heading.() -> Unit): Heading {
    val heading = Heading(text)
    block(heading)
    return heading
}

fun blockQuote(block: BlockQuote.() -> Unit): BlockQuote {
    val blockQuote = BlockQuote()
    block(blockQuote)
    return blockQuote
}

fun image(src: String, block: Image.() -> Unit): Image {
    val image = Image(src)
    block(image)
    return image
}

fun link(href: String, text: String, block: Link.() -> Unit): Link {
    val link = Link(href, text)
    block(link)
    return link
}

fun appear(child: SpectacleTag, block: Appear.() -> Unit): Appear {
    val appear = Appear(child)
    block(appear)
    return appear
}

fun text(text: String, block: Text.() -> Unit): Text {
    val textTag = Text(text)
    block(textTag)
    return textTag
}

fun code(source: String, lang: String, block: Code.() -> Unit): Code {
    val code = Code(source = source, lang = lang)
    block(code)
    return code
}

fun markdown(source: String): Markdown {
    return Markdown(source)
}