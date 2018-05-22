package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.Base
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
abstract class BaseModel : Base {
    @ReactProp
    @ValueMapValue
    override var italic: Boolean? = false

    @ReactProp
    @ValueMapValue
    override var bold: Boolean? = false

    @ReactProp
    @ValueMapValue
    override var caps: Boolean? = false

    @ReactProp
    @ValueMapValue
    override var margin: Int? = null

    @ReactProp
    @ValueMapValue
    override var padding: Int? = null

    @ReactProp
    @ValueMapValue
    override var textColor: String? = null

    @ReactProp
    @ValueMapValue
    override var textSize: String? = null

    @ReactProp
    @ValueMapValue
    override var textAlign: String? = null

    @ReactProp
    @ValueMapValue
    override var textFont: String? = null

    @ReactProp
    @ValueMapValue
    override var bgColor: String? = null

    @ReactProp
    @ValueMapValue
    override var bgDarken: Float? = null

}