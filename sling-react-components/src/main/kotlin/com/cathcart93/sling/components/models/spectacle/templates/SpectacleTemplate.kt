import com.cathcart93.sling.components.models.spectacle.impl.builder.*

fun spectacleTemplate(text: String = "Test"): Deck {
    return deck(FirstTheme) {
        transition = SlideTransition(listOf(SlideTransitionType, ZoomTransitionType))
        slide {
            heading(text) {
                fit = true
                size = 1
            }
            heading("Slide 1 2") {
                size = 3
            }
        }
        slide {
            bgColor = "secondary"
            heading("Slide 2") {
                size = 1
            }
            heading("Slide 2 h5") {
                size = 5
            }
            blockQuote {
                quote("Quote From Kotlin Template") {

                }
                cite("Unknown") {

                }
            }
            text("Test Text") {
                lineHeight = 3
                fit = true

            }
        }
    }
}