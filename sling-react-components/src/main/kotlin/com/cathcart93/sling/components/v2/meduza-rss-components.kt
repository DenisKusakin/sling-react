package com.cathcart93.sling.components.v2

import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

object MeduzaSpectacle : ComponentV2<String> {
    override fun render(props: String): Element {
        return RootComponentV2 {
            RootComponentProps(
                    content = Deck {
                        slides = getNewsItems(props).map {
                            Slide {
                                components = listOf(Text {
                                    text = it.title
                                })
                            }
                        }
                    },
                    url = "test"
            )
        }
    }

}

private fun getNewsItems(xml: String): List<NewsItem> {
    val xmlDoc: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(InputSource(StringReader(xml)))
    xmlDoc.documentElement.normalize()

    val items: NodeList = xmlDoc.getElementsByTagName("item")
    val resultItems = mutableListOf<NewsItem>()
    for (i in 0 until items.length) {
        var item = items.item(i)
        if (item.nodeType === Node.ELEMENT_NODE) {
            val elem = item as org.w3c.dom.Element
            val title = elem.getElementsByTagName("title").item(0).textContent
            val link = elem.getElementsByTagName("link").item(0).textContent
            val description = elem.getElementsByTagName("description").item(0).textContent
//            val image = elem.getElementsByTagName("enclosure").item(0).attributes.getNamedItem("renderUrl").textContent
            resultItems += NewsItem(title, link, description, "image")
        }
    }
    return resultItems
}

data class NewsItem(val title: String, val link: String, val description: String, val image: String)