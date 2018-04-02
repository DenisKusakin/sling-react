package com.cathcart93.sling.componentsv2.react

import com.cathcart93.sling.componentsv2.models.{BlockQuote, Component, DeckModel, HeadingModel}

trait SpectacleReactAdapters {
  val headingToReactAdapter: ModelToReactAdapter[HeadingModel] = HeadingReactAdapter
  val blockQuoteToReactAdapter: ModelToReactAdapter[BlockQuote] = BlockQuoteReactAdapter
  val deckToReactAdapter: ModelToReactAdapter[DeckModel] = DeckReactAdapter
  val componentToReactAdapter: ModelToReactAdapter[Component] = SpectacleComponentReactAdapter
}
