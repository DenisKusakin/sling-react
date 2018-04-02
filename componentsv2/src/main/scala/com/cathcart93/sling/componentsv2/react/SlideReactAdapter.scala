package com.cathcart93.sling.componentsv2.react

import com.cathcart93.sling.componentsv2.models.SlideModel

object SlideReactAdapter extends ModelToReactAdapter [SlideModel]{
  override def toReact(model: SlideModel): ReactComponent = {
    ReactComponent(
      componentName = "Slide",
      props = Map(
        "bgColor" -> ReactStringProp(model.bgColor),
        "textColor" -> ReactStringProp(model.textColor)
      ),
      children = model.components.map(x => ReactComponentProp(SpectacleComponentReactAdapter.toReact(x)))
    )
  }
}
