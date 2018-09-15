package com.cathcart93.sling.components.models.aempoc

import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.ReactTag
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toJson

interface AEMReactModel {
    fun toReact(isEditMode: Boolean = false): ReactElement

    fun toJson(isEditMode: Boolean): String {
        return toReact(isEditMode).toJson()
    }

    fun toJson(): String {
        return toReact(true).toJson()
    }
}