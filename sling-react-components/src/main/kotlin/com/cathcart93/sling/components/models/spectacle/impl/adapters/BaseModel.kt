package com.cathcart93.sling.components.models.spectacle.impl.adapters

import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
abstract class BaseModel {
    @ValueMapValue
    var italic: Boolean? = false

    @ValueMapValue
    var bold: Boolean? = false

    @ValueMapValue
    var caps: Boolean? = false

    @ValueMapValue
    var margin: Int? = null

    @ValueMapValue
    var padding: Int? = null

    @ValueMapValue
    var textColor: String? = null

    @ValueMapValue
    var textSize: String? = null

    @ValueMapValue
    var textAlign: String? = null

    @ValueMapValue
    var textFont: String? = null

    @ValueMapValue
    var bgColor: String? = null

    @ValueMapValue
    var bgDarken: Float? = null

}