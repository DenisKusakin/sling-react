package com.cathcart93.sling.components.models.spectacle.impl.builder

import com.cathcart93.sling.components.models.spectacle.api.*
import com.cathcart93.sling.components.models.spectacle.api.BlockQuote

fun spectacle(block: SpectacleContext.() -> Unit): DeckImpl {
    val spectacleContext = SpectacleContext()
    block(spectacleContext)
    return DeckImpl(
            children = spectacleContext.context.slides,
            fonts = spectacleContext.fonts,
            colors = spectacleContext.colors
    )
}

@Marker
class SpectacleContext : AuthorableContext() {
    val context = SlidesContext()
    val fonts = HashMap<String, String>()
    val colors = HashMap<String, String>()

    fun slides(block: SlidesContext.() -> Unit) {
        block(context)
    }

    fun primaryFont(primaryFont: String) {
        fonts.put("primary", primaryFont)
    }

    fun secondaryFont(secondaryFont: String) {
        fonts.put("secondary", secondaryFont)
    }

    fun primaryColor(primaryColor: String) {
        colors.put("primary", primaryColor)
    }

    fun secondaryColor(secondaryColor: String) {
        colors.put("secondary", secondaryColor)
    }

    fun tertiaryColor(tertiaryColor: String) {
        colors.put("tertiary", tertiaryColor)
    }

    fun quarternaryColor(quarternaryColor: String) {
        colors.put("quarternary", quarternaryColor)
    }
}

@Marker
class SlidesContext {
    val slides = ArrayList<Slide>()

    fun slide(block: SlideContext.() -> Unit) {
        val slideContext = SlideContext()
        block(slideContext)
        slides.add(slideContext.getSlide())
    }

    fun slide(slide: Slide) {
        slides.add(slide)
    }
}

@Marker
class SlideContext : AuthorableContext() {
    var bgColor: String? = null
    var textColor: String? = null
    val slideComponents = ArrayList<SlideComponent>()

    fun bgColor(bgColor: String) {
        this.bgColor = bgColor
    }

    fun textColor(textColor: String) {
        this.textColor = textColor
    }

    fun heading(text: String, block: HeadingContext.() -> Unit) {
        val headingContext = HeadingContext(text)
        block(headingContext)
        slideComponents.add(headingContext.getHeading())
    }

    fun text(text: String, block: TextContext.() -> Unit) {
        val textContext = TextContext(text)
        block(textContext)
        slideComponents.add(textContext.getText())
    }

    fun blockQuote(text: String, author: String) {
        slideComponents.add(BlockQuoteImpl(quote = text, author = author))
    }

    fun getSlide(): Slide {
        return SlideImpl(bgColor, textColor, slideComponents)
    }
}

@Marker
class HeadingContext(val text: String) : AuthorableContext() {
    var size: Int = 1
    var fit: Boolean = false

    fun size(size: Int) {
        this.size = size
    }

    fun fit() {
        this.fit = fit
    }

    fun getHeading(): HeadingImpl {
        return HeadingImpl(children = text, size = size, fit = fit)
    }
}

@Marker
class TextContext(val text: String) : AuthorableContext() {
    var fit: Boolean = false
    var lineHeight: Int = 1
    var textColor: String? = null

    fun fit() {
        this.fit = true
    }

    fun lineHeight(lineHeight: Int) {
        this.lineHeight = lineHeight
    }

    fun color(color: String?) {
        this.textColor = color
    }

    fun getText(): Text {
        return TextImpl(children = text, fit = fit, lineHeight = lineHeight, textColor = textColor)
    }
}

open class AuthorableContext {
    val __dialog_type = "dialogs/SimpleDialog"
    var __dialog: SimpleDialog? = null

    fun dialog(block: () -> SimpleDialog) {
        __dialog = block()
    }
}

@DslMarker
annotation class Marker

class SlideImpl(
        override var bgColor: String?,
        override var textColor:
        String?, override var children: List<SlideComponent>) : Slide {
    val __type = Constants.SLIDE
}

class HeadingImpl(
        override val children: String,
        override val size: Int = 1,
        override val caps: Boolean = false,
        override val textColor: String? = null,
        override val lineHeight: Int = 1,
        override val fit: Boolean = false) : com.cathcart93.sling.components.models.spectacle.api.Heading {
    val __type = Constants.HEADING
}

class DeckImpl(
        override val fonts: Map<String, String?> = emptyMap(),
        override val colors: Map<String, String?> = emptyMap(),
        override val children: List<Slide>) : Deck {
    val __type = Constants.DECK
}

class TextImpl(
        override val children: String,
        override val fit: Boolean,
        override val lineHeight: Int,
        override val textColor: String?
) : Text {
    val __type = Constants.TEXT
}

class BlockQuoteImpl(quote: String, author: String) : BlockQuote {
    val __type = Constants.BLOCK_QUOTE
    override val children = ArrayList<BlockQuote.BlockQuoteChildComponent>()

    init {
        children.add(QuoteImpl(quote))
        children.add(CiteImpl(author))
    }

    class QuoteImpl(text: String) : BlockQuote.Quote {
        val __type = "Quote"
        override val children = text
    }

    class CiteImpl(author: String) : BlockQuote.Cite {
        val __type = "Cite"
        override val children = author
    }
}