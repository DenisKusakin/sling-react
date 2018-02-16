package com.cathcart93.sling.components.models.spectacle.api

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp

/**
 * @author Denis_Kusakin. 2/15/2018.
 */
@ReactController(Constants.BLOCK_QUOTE)
interface BlockQuote : IReactController, SlideComponent {
    @ReactProp
    val children: List<BlockQuoteChildComponent>

    @ReactController("Quote")
    interface Quote : BlockQuoteChildComponent {
        @ReactProp
        val children: String
    }

    @ReactController("Cite")
    interface Cite : BlockQuoteChildComponent {
        @ReactProp
        val children: String
    }

    interface BlockQuoteChildComponent : IReactController
}