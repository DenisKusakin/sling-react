package com.cathcart93.sling.components.models.spectacle.templates

import com.cathcart93.sling.components.models.spectacle.api.Slide
import com.cathcart93.sling.components.models.spectacle.impl.builder.DeckImpl
import com.cathcart93.sling.components.models.spectacle.impl.builder.spectacle

fun spectacleTemplate(text: String = "Test"): DeckImpl {
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
            }
            slide(lastSlide)
        }
    }
}