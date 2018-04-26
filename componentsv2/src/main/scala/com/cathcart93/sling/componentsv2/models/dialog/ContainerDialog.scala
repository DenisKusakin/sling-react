package com.cathcart93.sling.componentsv2.models.dialog

import org.apache.sling.api.resource.Resource

case class ContainerDialog(
                            path: String,
                            childrenPaths: Seq[String],
                            allowedComponents: Seq[ContainerComponentItem]) extends ComponentDialog

case class ContainerComponentItem(name: String, title: String, description: String)

