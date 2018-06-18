package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.impl.builder.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.Number
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, adapters = [MeduzaDeckModel::class])
class MeduzaDeckModel : ReactModel {

    @SlingObject
    private lateinit var resource: Resource

    @ValueMapValue
    private var transitionDuration: Int? = null

    @ValueMapValue
    private var transition: String? = null

    @ValueMapValue
    private var progress: String? = null

    @ValueMapValue
    private var controls: Boolean = true

    @ValueMapValue
    private var theme: String = "default"

    @ValueMapValue
    private var xml: String? = null

    override fun toReact(isEditMode: Boolean): SpectacleTag {
        val slides = getNewsItems()
                .map {
                    slide {
                        image(it.image) {}
                        link(href = it.link, text = "Link to News") {

                        }
                        text(text = it.title) {}
                        comp(propertiesButton())
                    }
                }

        val lastSlide = slide {
            text("XML is empty. Configure it in the dialog") {

            }
            comp(propertiesButton())
        }
        return deck(theme.toTheme()) {
            transitionDuration = this@MeduzaDeckModel.transitionDuration
            transition = this@MeduzaDeckModel.transition?.toSlideTransition()
            controls = this@MeduzaDeckModel.controls
            progress = this@MeduzaDeckModel.progress?.toProgress()
            (if (slides.isEmpty()) listOf(lastSlide) else slides).forEach { slide(it) }
        }
    }

    private fun propertiesButton(): SlidePropertiesButton {
        return propertiesButton("Presentation Properties", resource.path) {
            text(
                    name = "transition",
                    title = "Transition (slide, zoom, fade, spin)",
                    value = transition ?: ""
            )
            text(
                    name = "transitionDuration",
                    title = "Transition Duration",
                    value = transitionDuration?.toString() ?: ""
            )
            select(
                    name = "progress",
                    title = "Progress icon",
                    value = if (this@MeduzaDeckModel.progress == null) "pacman" else this@MeduzaDeckModel.progress!!,
                    options = listOf(
                            SelectOption(label = "Pacman", value = "pacman"),
                            SelectOption(label = "Bar", value = "bar"),
                            SelectOption(label = "Number", value = "number"),
                            SelectOption(label = "Node", value = "none")
                    )
            )
            select(
                    name = "theme",
                    title = "Theme",
                    value = this@MeduzaDeckModel.theme,
                    options = listOf(
                            SelectOption(label = "Dark", value = "dark"),
                            SelectOption(label = "Pink", value = "pink"),
                            SelectOption(label = "Default", value = "default")
                    )
            )
            multilineText(
                    name = "xml",
                    title = "XML",
                    value = xml ?: ""
            )
        }
    }

    private fun getNewsItems(): List<NewsItem> {
        if (xml.isNullOrBlank()) {
            return emptyList()
        }
        val xmlDoc: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(InputSource(StringReader(xml)))
        xmlDoc.documentElement.normalize()

        val items: NodeList = xmlDoc.getElementsByTagName("item")
        val resultItems = mutableListOf<NewsItem>()
        for (i in 0 until items.length) {
            var item = items.item(i)
            if (item.nodeType === Node.ELEMENT_NODE) {
                val elem = item as Element
                val title = elem.getElementsByTagName("title").item(0).textContent
                val link = elem.getElementsByTagName("link").item(0).textContent
                val description = elem.getElementsByTagName("description").item(0).textContent
                val image = elem.getElementsByTagName("enclosure").item(0).attributes.getNamedItem("url").textContent
                resultItems += NewsItem(title, link, description, image)
            }
        }
        return resultItems
    }

    data class NewsItem(val title: String, val link: String, val description: String, val image: String)

    private fun String.toProgress(): Progress {
        return when (this) {
            "pacman" -> Pacman
            "bar" -> Bar
            "number" -> Number
            "none" -> None
            else -> Pacman
        }
    }

    private fun String.toTheme(): ExtendedDefaultTheme {
        return when (this) {
            "dark" -> DarkTheme
            "pink" -> PinkTheme
            "default" -> DefaultTheme
            else -> DarkTheme
        }
    }
}