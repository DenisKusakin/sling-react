package com.cathcart93.sling.componentsv2.react

import com.cathcart93.sling.componentsv2.models.HeadingModel

object HeadingReactAdapter extends ModelToReactAdapter[HeadingModel] {
  override def toReact(heading: HeadingModel): ReactComponent = {
    ReactComponent(
      componentName = "Heading",
      props = Map(
        "size" -> ReactIntProp(heading.size),
        "fit" -> ReactBooleanProp(heading.fit),
        "lineHeight" -> ReactIntProp(heading.lineHeight)
      ),
      children = Seq(ReactStringProp(heading.text))
    )
  }
}
