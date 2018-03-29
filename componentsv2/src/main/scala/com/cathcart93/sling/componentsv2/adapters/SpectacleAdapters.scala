package com.cathcart93.sling.componentsv2.adapters

/**
  * @author Denis_Kusakin. 3/29/2018.
  */
trait SpectacleAdapters extends HeadingAdapter with BlockQuoteAdapter{
  implicit object HeadingAdapter extends HeadingAdapter
  implicit object BlockQuoteAdapter extends BlockQuoteAdapter
}
