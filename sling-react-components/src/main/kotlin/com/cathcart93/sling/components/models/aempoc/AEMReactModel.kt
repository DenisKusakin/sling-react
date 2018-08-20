package com.cathcart93.sling.components.models.aempoc

import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.ReactTag
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toJson

interface AEMReactModel {
    fun toReact(): ReactTag

    fun toJson(): String {
        return toReact().toReactElement().toJson()
    }
}