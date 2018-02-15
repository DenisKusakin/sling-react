package com.cathcart93.sling.components.models.spectacle.api

/**
 * @author Denis_Kusakin. 2/15/2018.
 */
interface BlockQuote : SlideComponent {
    val children: List<BlockQuoteChildComponent>
    val quote: String
    val cite: String

    interface Quote : BlockQuoteChildComponent {
        val children: String
    }

    interface Cite : BlockQuoteChildComponent {
        val children: String
    }

    interface BlockQuoteChildComponent
}