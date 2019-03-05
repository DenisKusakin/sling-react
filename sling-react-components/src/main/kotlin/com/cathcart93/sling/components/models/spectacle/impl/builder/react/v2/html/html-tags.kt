package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.html

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.ElementDescriptor
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.createComponent
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.createElement

sealed class TagContentDescriptor
data class TagDescriptor(val tagProps: HtmlTagProps) : TagContentDescriptor()
data class StringDescriptor(val string: String) : TagContentDescriptor()

class HtmlTagProps(val tagName: String, val attributes: TagAttributes)


data class TagAttributes(val attributes: List<Pair<String, String>>)
data class HProps(val title: String, val attributes: TagAttributes = TagAttributes(emptyList()))

val h1 = createComponent { props: HProps, children: List<ElementDescriptor> ->
    val tagProperties = HtmlTagProps(tagName = "h1", attributes = props.attributes)
    createElement("h1", tagProperties, children)
}

val h2 = createComponent { props: HProps, children: List<ElementDescriptor> ->
    createElement("h2", props, children)
}

val h3 = createComponent { props: HProps, children: List<ElementDescriptor> ->
    createElement("h3", props, children)
}

val div = createComponent { props: TagAttributes, children: List<ElementDescriptor> ->
    createElement("div", props, children)
}

sealed class LinkTarget
object Blank : LinkTarget()
object NewTab : LinkTarget()

data class LinkProps(val href: String, val target: LinkTarget = Blank, val attributes: TagAttributes)

val a = createComponent { props: LinkProps, children: List<ElementDescriptor> ->
    createElement("a", props, children)
}