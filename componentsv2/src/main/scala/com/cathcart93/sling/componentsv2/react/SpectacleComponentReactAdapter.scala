package com.cathcart93.sling.componentsv2.react

import com.cathcart93.sling.componentsv2.models.{BlockQuote, Component, DeckModel, HeadingModel}

object SpectacleComponentReactAdapter extends ModelToReactAdapter[Component]{
  override def toReact(model: Component): ReactComponent = {
    model match {
      case x: HeadingModel => HeadingReactAdapter.toReact(x)
      case x: BlockQuote => BlockQuoteReactAdapter.toReact(x)
      case x: DeckModel => DeckReactAdapter.toReact(x)
      case _ => ErrorReactComponent("Component")
    }
  }
}
