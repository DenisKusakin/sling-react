package com.cathcart93.sling.components.models.spectacle.templates

import com.cathcart93.sling.components.models.spectacle.api.Slide
import com.cathcart93.sling.components.models.spectacle.impl.adapters.DeckModel
import com.cathcart93.sling.components.models.spectacle.impl.builder.DeckImpl
import com.cathcart93.sling.components.models.spectacle.impl.builder.spectacle
import org.apache.sling.api.resource.Resource
import java.util.*

fun spectacleTemplate(rootResource: Resource): DeckImpl {
    val deck = rootResource.adaptTo(DeckModel::class.java)

    return spectacle {
        Optional.ofNullable(deck)
                .map { deck!!.primaryColor }
                .ifPresent { primaryColor(it) }
        Optional.ofNullable(deck)
                .map { deck!!.secondaryColor }
                .ifPresent { secondaryColor(it) }
        Optional.ofNullable(deck)
                .map { deck!!.tertiaryColor }
                .ifPresent { tertiaryColor(it) }
        Optional.ofNullable(deck)
                .map { deck!!.quarternaryColor }
                .ifPresent { quarternaryColor(it) }

        slides {
            //Some slides from content
            rootResource.children
                    .mapNotNull { it.adaptTo(Slide::class.java) }
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