package com.cathcart93.sling.components.models.aempoc

import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.ReactTag
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toJson

interface AEMReactModel {
    fun toReact(): ReactElement

    fun toJson(): String {
        return toReact().toJson()
    }
}