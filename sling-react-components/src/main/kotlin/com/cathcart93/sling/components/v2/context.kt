package com.cathcart93.sling.components.v2

data class EditModeContext(val isEditMode: Boolean)

fun isEditMode(editModeConsumer: (Boolean) -> Element): Element {
    return consumeContext(EditModeContext::class.java) {
        editModeConsumer(it.isEditMode)
    }
}

data class ImageSrcContext(val buildUrl: (String) -> String)