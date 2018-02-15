package com.cathcart93.sling.components.models.spectacle

import com.cathcart93.sling.components.models.spectacle.api.Slide
import com.cathcart93.sling.components.models.spectacle.api.SlideComponent

fun spectacle(block: SpectacleContext.() -> Unit): Deck {
    val spectacleContext = SpectacleContext()
    block(spectacleContext)
    return Deck(spectacleContext.context.slides)
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

    fun getHeading(): Heading {
        return Heading(text, size, fit)
    }
}


class SlideImpl(override val bgColor: String?, override val textColor: String?, override val children: List<SlideComponent>) : Slide {
    val __type = "Slide"
}

class Heading(val children: String, val size: Int = 1, val fit: Boolean = false) : SlideComponent {
    val __type = "Heading"
}

class Deck(val children: List<Slide>) {
    val __type = "Deck"
}