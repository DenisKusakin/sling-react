package com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toReactProp

interface ReactTag {
    fun toReactElement(): ReactElement
}

class Heading(private val text: String) : ReactTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(
                name = "Heading",
                props = mapOf("heading" to text.toReactProp())
        )
    }
}

class Parsys(private val children: List<ReactElement>) : ReactTag {
    override fun toReactElement(): ReactElement {
        return ReactElement(
                name = "Parsys",
                children = children
        )
    }

}