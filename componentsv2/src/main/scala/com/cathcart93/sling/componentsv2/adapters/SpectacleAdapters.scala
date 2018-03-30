package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models._

/**
  * @author Denis_Kusakin. 3/29/2018.
  */
trait SpectacleAdapters extends ResourceAdaptableImplicit{
  implicit val headingAdapter: ResourceAdapter[HeadingModel] = HeadingAdapter
  implicit val blockQuoteAdapter: ResourceAdapter[BlockQuote] = BlockQuoteAdapter
  implicit val componentAdapter: ResourceAdapter[Component] = SpectacleComponentAdapter
  implicit val slideAdapter: ResourceAdapter[SlideModel] = SlideAdapter
  implicit val deckAdapter: ResourceAdapter[DeckModel] = DeckAdapter
}
