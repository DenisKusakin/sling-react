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
                    .filter { it is Slide }
                    .map { it as Slide }
            (if (!isEditMode) slides else slides + lastSlide).forEach { slide(it) }
        }
    }

    fun propertiesButton(): SlidePropertiesButton {
        return propertiesButton(resource.path) {
            text(
                    name = "transition",
                    title = "Transition (slide, zoom, fade, spin)",
                    value = if (this@DeckModel.transition == null) "" else this@DeckModel.transition!!
            )
            text(
                    name = "transitionDuration",
                    title = "Transition Duration",
                    value = if (this@DeckModel.transitionDuration == null)
                        ""
                    else this@DeckModel.transitionDuration!!.toString()
            )
            select(
                    name = "progress",
                    title = "Progress icon",
                    value = if(this@DeckModel.progress == null) "pacman" else this@DeckModel.progress!!,
                    options = listOf(
                            SelectOption(label = "Pacman", value = "pacman"),
                            SelectOption(label = "Bar", value = "bar"),
                            SelectOption(label = "Number", value = "number"),
                            SelectOption(label = "Node", value = "none")
                    )
            )
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