package com.cathcart93.sling.components.models.spectacle.templates

import com.cathcart93.sling.components.models.spectacle.api.Slide
import com.cathcart93.sling.components.models.spectacle.impl.adapters.DeckModel
import com.cathcart93.sling.components.models.spectacle.impl.adapters.SlideModel
import com.cathcart93.sling.components.models.spectacle.impl.builder.DeckImpl
import com.cathcart93.sling.components.models.spectacle.impl.builder.spectacle
import org.apache.sling.api.resource.Resource
import java.util.*

fun spectacleTemplate(rootResource: Resource): DeckImpl {
    val deck = rootResource.adaptTo(DeckModel::class.java)

    return spectacle {
        slides {
            //Some slides from content
            rootResource.children
                    .mapNotNull { it.adaptTo(SlideModel::class.java) }
                    .forEach { slide(it) }
            slide {
                heading("One more Slide!") {

                }
                text("This slide is not a content one!") {
                    color("green")
                }
            }
        }
    }
}

fun spectacleTemplate2(text: String = "Test", lastSlide: Slide): DeckImpl {
    return spectacle {
        slides {
            slide {
                heading(text) {
                    fit()
                    size(1)
                }
                heading("Slide 1 2") {
                    size(3)
                }
            }
            slide {
                heading("Slide 2") {
                    size(1)
                }
                heading("Slide 2 h5") {
                    size(5)
                }
                blockQuote(text = "Quote From Kotlin Template", author = "Unknown")
                text("Test Text") {
                    lineHeight(2)
                    fit()

                }
            }
            slide(lastSlide)
        }
    }
}