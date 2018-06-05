package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.components.models.spectacle.impl.builder.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.Number
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@ReactController(Constants.DECK)
class DeckModel : IReactController, ReactModel {

    @SlingObject
    private lateinit var resource: Resource

//    @ValueMapValue
//    private var primaryColor: String? = null
//
//    @ValueMapValue
//    private var secondaryColor: String? = null
//
//    @ValueMapValue
//    private var tertiaryColor: String? = null
//
//    @ValueMapValue
//    private var quarternaryColor: String? = null
//
//    @ValueMapValue
//    private var primaryFont: String? = null
//
//    @ValueMapValue
//    private var secondaryFont: String? = null

    @ValueMapValue
    private var transitionDuration: Int? = null

    @ValueMapValue
    private var transition: String? = null

    @ValueMapValue
    private var progress: String? = null

    @ValueMapValue
    private var controls: Boolean = true

    override fun toReact(isEditMode: Boolean): SpectacleTag {
        val lastSlide = slide {
            heading("This Slide does not exist in content. Would you like to create it?") {

            }
            comp(AddSlideButton(resourcePath = "${resource.path}/"))
        }
        return deck(FirstTheme) {
            transitionDuration = this@DeckModel.transitionDuration
            transition = this@DeckModel.transition?.toSlideTransition()
            controls = this@DeckModel.controls
            progress = this@DeckModel.progress?.toProgress()
            val slides = resource.children
                    .mapNotNull { it.adaptTo(SlideModel::class.java) }
                    .map { (it as ReactModel).toReact(isEditMode) }
                    .filter { it is com.cathcart93.sling.components.models.spectacle.impl.builder.Slide }
                    .map { it as com.cathcart93.sling.components.models.spectacle.impl.builder.Slide }
            (if (!isEditMode) slides else slides + lastSlide).forEach { slide(it) }
        }
    }

    private fun String.toProgress(): Progress {
        return when (this) {
            "pacman" -> Pacman
            "bar" -> Bar
            "number" -> Number
            "none" -> None
            else -> Pacman
        }
    }
}