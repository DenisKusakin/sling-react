package com.cathcart93.sling.components.models.spectacle

import com.cathcart93.sling.components.models.spectacle.api.Slide

fun spectacleTemplate(text: String = "Test"): Deck {
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

fun spectacleTemplate2(text: String = "Test", lastSlide: Slide): Deck {
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