package com.cathcart93.sling.componentsv2.models

import com.cathcart93.sling.componentsv2.models.dialog.{ComponentDialog, SimpleDialogModel}

case class DeckModel(
                      slides: Seq[SlideModel],
                      primary: Option[Color] = None,
                      secondary: Option[Color] = None,
                      tertiary: Option[Color] = None,
                      quarternary: Option[Color] = None,
                      dialog: Option[ComponentDialog] = None
                    ) extends Component
