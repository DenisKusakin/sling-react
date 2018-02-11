package com.cathcart93.sling.components.models.spectacle

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue
import javax.annotation.PostConstruct

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@ReactController("Deck")
class DeckModel : IReactController {

    @SlingObject
    private lateinit var resource: Resource

    @ReactProp("children")
    private lateinit var slides: List<Slide>

    @ReactProp("__dialog")
    private lateinit var dialog: Any

    @ValueMapValue
    private var primaryColor: String? = null

    @ValueMapValue
    private var secondaryColor: String? = null

    @ValueMapValue
    private var tertiaryColor: String? = null

    @ValueMapValue
    private var quarternaryColor: String? = null

    @ValueMapValue
    private var primaryFont: String? = null

    @ValueMapValue
    private var secondaryFont: String? = null

    @ReactProp
    private var colors = HashMap<String, String?>()

    @ReactProp
    private var fonts = HashMap<String, String?>()

    @PostConstruct
    fun init() {
        slides = resource.children.mapNotNull { it.adaptTo(SlideModel::class.java) }
        val dialog = containerDialog(resource) {
            component("Slide")
        }

        val theme = dialog(resource) {
            color("primaryColor", "Primary Color")
            color("secondaryColor", "Secondary Color")
            color("tertiaryColor", "Tertiary Color")
            color("quarternaryColor", "Quarternary Color")

            select("primaryFont", "Primary Font") {
                option("Montserrat", "Montserrat")
                option("Helvetica", "Helvetica")
            }
            select("secondaryFont", "Secondary Font") {
                option("Montserrat", "Montserrat")
                option("Helvetica", "Helvetica")
            }
        }

        this.dialog = ThemedContainer(dialog, theme)

        colors.put("primary", primaryColor)
        colors.put("secondary", secondaryColor)
        colors.put("tertiary", tertiaryColor)
        colors.put("quarternary", quarternaryColor)

        fonts.put("primary", primaryFont)
        fonts.put("secondary", secondaryFont)
    }

}