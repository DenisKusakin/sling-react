package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.ResourceTypesConstants
import com.cathcart93.sling.components.models.spectacle.impl.builder.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.Number
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = [DeckModel::class, ReactModel::class],
        resourceType = [ResourceTypesConstants.DECK]
)
class DeckModel : ReactModel {

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

    @ValueMapValue
    private var theme: String = "default"

    override fun render(context: RenderContext): ReactElement {
        val isEditMode = context.isEditMode
        val slides = resource.children
                .mapNotNull { it.adaptTo(SlideModel::class.java) }

//                .map { (it as ReactModel).render(isEditMode) }
//                .filter { it is Slide }
//                .map { it as Slide }

        val lastSlide = slide {
            val lastSlide = slides.lastOrNull()
            if (lastSlide != null) {
                bgColor = lastSlide.bgColor
            }
            text("This Slide does not exist in content. Would you like to create it?") {

            }
            comp(AddSlideButton(resourcePath = "${resource.path}/").render())
        }.render()
        val slideReactElements = slides.map { it.render(context) }
        return deck(theme.toTheme()) {
            transitionDuration = this@DeckModel.transitionDuration
            transition = this@DeckModel.transition?.toSlideTransition()
            controls = this@DeckModel.controls
            progress = this@DeckModel.progress?.toProgress()
            (if (!isEditMode) slideReactElements else slideReactElements + lastSlide).forEach { slide(it) }
        }.render()
    }

    fun propertiesButton(): SlidePropertiesButton {
        return propertiesButton("Presentation Properties", resource.path) {
            text(
                    name = "transition",
                    title = "Transition (slide, zoom, fade, spin)",
                    value = transition ?: ""
            )
            text(
                    name = "transitionDuration",
                    title = "Transition Duration",
                    value = transitionDuration?.toString() ?: ""
            )
            select(
                    name = "progress",
                    title = "Progress icon",
                    value = if (this@DeckModel.progress == null) "pacman" else this@DeckModel.progress!!,
                    options = listOf(
                            SelectOption(label = "Pacman", value = "pacman"),
                            SelectOption(label = "Bar", value = "bar"),
                            SelectOption(label = "Number", value = "number"),
                            SelectOption(label = "Node", value = "none")
                    )
            )
            select(
                    name = "theme",
                    title = "Theme",
                    value = this@DeckModel.theme,
                    options = listOf(
                            SelectOption(label = "Dark", value = "dark"),
                            SelectOption(label = "Pink", value = "pink"),
                            SelectOption(label = "Default", value = "default")
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

    private fun String.toTheme(): ExtendedDefaultTheme {
        return when (this) {
            "dark" -> DarkTheme
            "pink" -> PinkTheme
            "default" -> DefaultTheme
            else -> DarkTheme
        }
    }
}