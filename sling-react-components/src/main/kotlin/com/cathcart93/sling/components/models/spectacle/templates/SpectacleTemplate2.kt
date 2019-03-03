import com.cathcart93.sling.components.models.spectacle.impl.builder.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement

fun spectacleTemplate2(text: String = "Test"): ReactElement {
    return deck(DarkTheme) {
        transition = SlideTransition(listOf(SlideTransitionType, ZoomTransitionType))
        slide {
            heading(text = text) {
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
    }.render()
}