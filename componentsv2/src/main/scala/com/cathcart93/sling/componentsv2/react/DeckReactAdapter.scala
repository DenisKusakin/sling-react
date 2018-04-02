package com.cathcart93.sling.componentsv2.react

import com.cathcart93.sling.componentsv2.models.DeckModel

object DeckReactAdapter extends ModelToReactAdapter[DeckModel] {
  override def toReact(model: DeckModel): ReactComponent = {
    ReactComponent(
      componentName = "Deck",
      children = model.slides.map(x => ReactComponentProp(SlideReactAdapter.toReact(x)))
    )
  }
}
