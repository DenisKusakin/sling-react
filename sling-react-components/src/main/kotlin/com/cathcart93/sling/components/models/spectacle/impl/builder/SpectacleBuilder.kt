package com.cathcart93.sling.components.models.spectacle.impl.builder

import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.components.models.spectacle.api.Deck
import com.cathcart93.sling.components.models.spectacle.api.Slide
import com.cathcart93.sling.components.models.spectacle.api.SlideComponent

fun spectacle(block: SpectacleContext.() -> Unit): DeckImpl {
    val spectacleContext = SpectacleContext()
    block(spectacleContext)
    return DeckImpl(children = spectacleContext.context.slides)
}

class SpectacleContext() {
    val context = SlidesContext()

    fun slides(block: SlidesContext.() -> Unit) {
        block(context)
    }
}

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

class SlideContext {
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

    fun getSlide(): Slide {
        return SlideImpl(bgColor, textColor, slideComponents)
    }
}

class HeadingContext(val text: String) {
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


class SlideImpl(
        override val bgColor: String?,
        override val textColor:
        String?, override val children: List<SlideComponent>) : Slide {
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
        override val primaryColor: String? = null,
        override val secondaryColor: String? = null,
        override val tertiaryColor: String? = null,
        override val quarternaryColor: String? = null,
        override val primaryFont: String? = null,
        override val secondaryFont: String? = null,
        override val fonts: Map<String, String?> = emptyMap(),
        override val colors: Map<String, String?> = emptyMap(),
        override val children: List<Slide>) : Deck {
    val __type = Constants.DECK
}