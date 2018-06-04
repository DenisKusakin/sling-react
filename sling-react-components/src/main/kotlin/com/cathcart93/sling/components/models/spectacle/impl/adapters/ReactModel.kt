package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.impl.builder.SpectacleTag

interface ReactModel {
    fun toReact(isEditMode: Boolean = false): SpectacleTag
}