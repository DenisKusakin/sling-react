package com.cathcart93.sling.componentsv2.react

import com.cathcart93.sling.componentsv2.models.{BlockQuote, Component, DeckModel, HeadingModel}

trait SpectacleReactAdapters {
  implicit val headingToReactAdapter: ModelToReactAdapter[HeadingModel] = HeadingReactAdapter
  implicit val blockQuoteToReactAdapter: ModelToReactAdapter[BlockQuote] = BlockQuoteReactAdapter
  implicit val deckToReactAdapter: ModelToReactAdapter[DeckModel] = DeckReactAdapter
  implicit val componentToReactAdapter: ModelToReactAdapter[Component] = SpectacleComponentReactAdapter
}
