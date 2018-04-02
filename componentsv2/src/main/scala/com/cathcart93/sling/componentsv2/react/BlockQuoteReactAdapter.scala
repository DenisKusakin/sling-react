package com.cathcart93.sling.componentsv2.react

import com.cathcart93.sling.componentsv2.models.{BlockQuote, HeadingModel}

object BlockQuoteReactAdapter extends ModelToReactAdapter[BlockQuote] {
  override def toReact(blockQuote: BlockQuote): ReactComponent = {
    ReactComponent(
      componentName = "BlockQuote",
      children = Seq(
        ReactComponentProp(ReactComponent(componentName = "Quote", children = Seq(ReactStringProp(blockQuote.quote)))),
        ReactComponentProp(ReactComponent(componentName = "Cite", children = Seq(ReactStringProp(blockQuote.cite))))
      )
    )
  }
}
