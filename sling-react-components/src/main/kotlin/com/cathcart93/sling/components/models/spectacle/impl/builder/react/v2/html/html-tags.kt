package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.html

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.createComponent
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.createElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2.props
import with

data class HProps(val title: String)

val h1 = createComponent { props: HProps ->
    //val tagProperties = HtmlTagProps(tagName = "h1", attributes = props.attributes)
    //createElement("h1", tagProperties, children)
    createElement("components/h1", props {
        "title" to props.title
    }, emptyList())
}

val h2 = createComponent { props: HProps ->
    createElement("components/h2", props {
        "title" to props.title
    }, emptyList())
}

val h3 = createComponent { props: HProps ->
    createElement("components/h3", props {
        "title" to props.title
    }, emptyList())
}

sealed class LinkTarget
object Blank : LinkTarget()
object NewTab : LinkTarget()

data class LinkProps(val href: String, val target: LinkTarget = Blank, val title: String)

val link = createComponent { props: LinkProps ->
    createElement("components/link", props {
        "href" to props.href
        "target" to props.target.let {
            when (it) {
                is Blank -> "blank"
                is NewTab -> "new"
            }
        }
        "title" to props.title
    }, emptyList())
}

val newWindowLink = createComponent { (title, href): Pair<String, String> ->
    link.with(LinkProps(href, Blank, title))
}

val googleLink = createComponent {
    link.with(LinkProps(title = "Google", href = "https://google.com", target = Blank))
}