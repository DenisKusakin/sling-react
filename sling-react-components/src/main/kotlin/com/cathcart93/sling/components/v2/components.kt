package com.cathcart93.sling.components.v2

object RootComponentV2 : ComponentV2<RootComponentProps> {
    override fun render(props: RootComponentProps): Element {
        return "SpectacleAuthorRoot" {
            "url" to props.url
            "content" to props.content
        }
    }
}

object Deck : ComponentV2<DeckProps> {
    override fun render(props: DeckProps): Element {
        return "Deck" {
            "colors" to {

            }
            "fonts" to {

            }
            props.autoplay?.let { "autoplay" to it }
            props.autoplayDuration?.let { "autoplayDuration" to it }
            props.autoplayLoop?.let { "autoplayLoop" to it }
            props.autoplayOnStart?.let { "autoplayOnStart" to it }
            props.controls?.let { "controls" to it }
            props.contentHeight?.let { "contentHeight" to it }
            props.contentWidth?.let { "contentWidth" to it }
            props.disableKeyboardControls?.let { "disableKeyboardControls" to it }
            props.progress?.let { "progress" to it }
            props.showFullscreenControl?.let { "showFullscreenControl" to it }
            props.transitionDuration?.let { "transitionDuration" to it }
            "children" to props.slides.map { ElementProp(it) }
        }
    }

}

object Slide : ComponentV2<SlideProps> {
    override fun render(props: SlideProps): Element {
        return "Slide" {
            props.asProps().value.forEach { (t: String, u: PrimitiveProp) -> t to u }
            props.align?.let { "align" to it }
            props.controlColor?.let { "controlColor" to it }
            props.goTo?.let { "goTo" to it }
            props.id?.let { "id" to it }
            props.maxHeight?.let { "maxHeight" to it }
            props.maxWidth?.let { "maxWidth" to it }
            props.notes?.let { "notes" to it }
            props.state?.let { "state" to it }
            props.transitionDuration?.let { "transitionDuration" to it }
            "children" to props.components.map { ElementProp(it) }
        }
    }

}

object Text : ComponentV2<TextProps> {
    override fun render(props: TextProps): Element {
        return "Text" {
            props.asProps().value.forEach { (t: String, u: PrimitiveProp) -> t to u }
            props.fit?.let { "fit" to it }
            props.lineHeight?.let { "lineHeight" to it }
            "children" to props.text
        }
    }
}

object BlockQuote : ComponentV2<BlockQuoteProps> {
    override fun render(props: BlockQuoteProps): Element {
        return "BlockQuote" {
            "children" to ArrayProp(listOf(
                    "Quote" {
                        props.quoteProps?.asProps()?.value?.forEach { (t: String, u: PrimitiveProp) -> t to u }
                        "children" to (props.quoteProps?.text ?: "")
                    },
                    "Cite" {
                        props.citeProps?.asProps()?.value?.forEach { (t: String, u: PrimitiveProp) -> t to u }
                        "children" to (props.citeProps?.text ?: "")
                    }
            ).map { ElementProp(it) })
        }
    }

}


/*
DSL
 */

operator fun Slide.invoke(propsBuilder: SlideProps.() -> Unit): Element {
    val slidePropsBuilder = SlideProps()
    propsBuilder(slidePropsBuilder)
    return FunctionalElement(this, slidePropsBuilder)
}

operator fun Deck.invoke(propsBuilder: DeckProps.() -> Unit): Element {
    val deckPropsBuilder = DeckProps()
    propsBuilder(deckPropsBuilder)
    return FunctionalElement(this, deckPropsBuilder)
}

operator fun Text.invoke(propsBuilder: TextProps.() -> Unit): Element {
    val textPropsBuilder = TextProps()
    propsBuilder(textPropsBuilder)
    return FunctionalElement(this, textPropsBuilder)
}

operator fun BlockQuote.invoke(propsBuilder: BlockQuoteProps.() -> Unit): Element {
    val props = BlockQuoteProps()
    propsBuilder(props)
    return FunctionalElement(this, props)
}

/*
DSL END
 */

class DeckProps {
    var autoplay: Boolean? = null
    var autoplayDuration: Int? = null
    var autoplayLoop: Boolean? = null
    var autoplayOnStart: Boolean? = null
    var controls: Boolean? = null
    var contentHeight: Int? = null
    var contentWidth: Int? = null
    var disableKeyboardControls: Boolean? = null
    var progress: String? = null
    var showFullscreenControl: Boolean? = null
    var transitionDuration: Int? = null
    var slides: List<Element> = emptyList()
}

class SlideProps : BaseProps() {
    var align: String? = null
    var controlColor: String? = null
    var goTo: Int? = null
    var id: String? = null
    var maxHeight: Int? = null
    var maxWidth: Int? = null
    var notes: String? = null
    var state: String? = null
    var transitionDuration: Int? = null
    var components: List<Element> = emptyList()
}

class BlockQuoteProps : BaseProps() {
    var quoteProps: QuoteProps? = null
    var citeProps: CiteProps? = null

    fun quote(quoteProps: QuoteProps.() -> Unit) {
        val props = QuoteProps()
        quoteProps(props)
        this.quoteProps = props
    }

    fun cite(citeProps: CiteProps.() -> Unit) {
        val props = CiteProps()
        citeProps(props)
        this.citeProps = props
    }
}

class TextProps : BaseProps() {
    var fit: Boolean? = null
    var lineHeight: Int? = null
    var text: String = ""
}

class QuoteProps : BaseProps() {
    var text: String = ""
}

class CiteProps : BaseProps() {
    var text: String = ""
}

data class RootComponentProps(val content: Element, val url: String)