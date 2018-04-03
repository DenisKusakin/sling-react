package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.adapters.authoring.HeadingAuthoringAdapter
import com.cathcart93.sling.componentsv2.models.dialog.SimpleDialogModel

trait SpectacleAuthoringAdapters{
  val headingAdapter: ResourceAdapter[SimpleDialogModel] = HeadingAuthoringAdapter
}
