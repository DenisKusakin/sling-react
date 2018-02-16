package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.components.models.spectacle.api.Deck
import com.cathcart93.sling.components.models.spectacle.api.Slide
import com.cathcart93.sling.components.models.spectacle.dialogs.deckDialog
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
@ReactController(Constants.DECK)
class DeckModel : IReactController, Deck {

    @SlingObject
    private lateinit var resource: Resource

    @ReactProp
    override lateinit var children: List<Slide>

    @ReactProp("__dialog")
    private lateinit var dialog: Any

    @ValueMapValue
    var primaryColor: String? = null

    @ValueMapValue
    var secondaryColor: String? = null

    @ValueMapValue
    var tertiaryColor: String? = null

    @ValueMapValue
    var quarternaryColor: String? = null

    @ValueMapValue
    var primaryFont: String? = null

    @ValueMapValue
    var secondaryFont: String? = null

    @ReactProp
    override var colors = HashMap<String, String?>()

    @ReactProp
    override var fonts = HashMap<String, String?>()

    @PostConstruct
    fun init() {
        children = resource.children.mapNotNull { it.adaptTo(SlideModel::class.java) }

        colors.put("primary", primaryColor)
        colors.put("secondary", secondaryColor)
        colors.put("tertiary", tertiaryColor)
        colors.put("quarternary", quarternaryColor)

        fonts.put("primary", primaryFont)
        fonts.put("secondary", secondaryFont)

        dialog = deckDialog(resource)
    }

}