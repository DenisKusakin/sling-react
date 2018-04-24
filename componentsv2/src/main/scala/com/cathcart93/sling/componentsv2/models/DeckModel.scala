package com.cathcart93.sling.componentsv2.models

case class DeckModel(
                      slides: Seq[SlideModel],
                      primary: Option[Color] = None,
                      secondary: Option[Color] = None,
                      tertiary: Option[Color] = None,
                      quarternary: Option[Color] = None,
                    ) extends Component
