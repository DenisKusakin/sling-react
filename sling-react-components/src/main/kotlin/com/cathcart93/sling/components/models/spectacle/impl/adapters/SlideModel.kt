package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.components.models.spectacle.impl.builder.*
import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(adaptables = [Resource::class], defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@ReactController(Constants.SLIDE)
class SlideModel : IReactController, ReactModel, BaseModel() {
    @SlingObject
    private lateinit var resource: Resource

    @ValueMapValue
    private var transition: String? = null

    @ValueMapValue
    private var transitionDuration: Int? = null

    @ValueMapValue
    private var align: String? = null

    @ValueMapValue
    private var id: String? = null

    @ValueMapValue
    private var notes: String? = null

    override fun toReact(isEditMode: Boolean): SpectacleTag {
        val components = listOf(
                ContainerComponent(name = "Heading", description = "Heading component"),
                ContainerComponent(name = "Text", description = "Text component"),
                ContainerComponent(name = "Link", description = "Link component"),
                ContainerComponent(name = "Image", description = "Image component"),
                ContainerComponent(name = "BlockQuote", description = "BlockQuote component"),
                ContainerComponent(name = "Code", description = "Code component")
        )
        val children = resource.children
                .mapNotNull { it.adaptTo(IReactController::class.java) }
                .filter { it is ReactModel }
                .map { it as ReactModel }
                .map { it.toReact(isEditMode) }

        return slide {
            transition = this@SlideModel.transition?.toSlideTransition()
            transitionDuration = this@SlideModel.transitionDuration
            align = this@SlideModel.align?.toSlideAlign()
            id = this@SlideModel.id
            notes = this@SlideModel.notes
            bgColor = this@SlideModel.bgColor
            if (isEditMode) {
                comp(Container(children = children, components = components, resourcePath = "${resource.path}/"))
                comp(
                        SystemButtonsContainer(
                                EditModeToggler,
                                DeleteSlideButton(resource.path),
                                propertiesButton("Slide Properties", resource.path) {
                                    select(
                                            name = "textColor",
                                            title = "Text Color",
                                            value = if (this@SlideModel.textColor == null) "" else this@SlideModel.textColor!!,
                                            options = listOf(
                                                    SelectOption(label = "Primary", value = "primary"),
                                                    SelectOption(label = "Secondary", value = "secondary"),
                                                    SelectOption(label = "Tertiary", value = "tertiary"),
                                                    SelectOption(label = "Quarternary", value = "quarternary"),
                                                    SelectOption(label = "Default", value = "")
                                            )
                                    )
                                    select(
                                            name = "bgColor",
                                            title = "Background Color",
                                            value = if (this@SlideModel.bgColor == null) "" else this@SlideModel.bgColor!!,
                                            options = listOf(
                                                    SelectOption(label = "Primary", value = "primary"),
                                                    SelectOption(label = "Secondary", value = "secondary"),
                                                    SelectOption(label = "Tertiary", value = "tertiary"),
                                                    SelectOption(label = "Quarternary", value = "quarternary"),
                                                    SelectOption(label = "Default", value = "")
                                            )
                                    )
                                    text(
                                            name = "transition",
                                            title = "Transition (slide, zoom, fade, spin)",
                                            value = if (this@SlideModel.transition == null) "" else this@SlideModel.transition!!
                                    )
                                    text(
                                            name = "transitionDuration",
                                            title = "Transition Duration",
                                            value = if (this@SlideModel.transitionDuration == null)
                                                ""
                                            else this@SlideModel.transitionDuration!!.toString()
                                    )
                                    text(
                                            name = "align",
                                            title = "Align",
                                            value = if (align == null) "" else this@SlideModel.align!!
                                    )
                                    multilineText(
                                            name = "notes",
                                            title = "Notes",
                                            value = if (notes == null) "" else this@SlideModel.notes!!
                                    )
                                    text(
                                            name = "id",
                                            title = "Slide ID",
                                            value = if (id == null) "" else this@SlideModel.id!!
                                    )
                                },
                                resource.parent?.adaptTo(DeckModel::class.java)!!.propertiesButton()
                        )
                )
            } else {
                children.forEach { comp(it) }
            }
        }
    }

    private fun String.toSlideAlign(): SlideAlign {
        fun stringToAlign(s: String): Align {
            return when (s) {
                "flex-start" -> FlexStart
                "center" -> Center
                "flex-end" -> FlexEnd
                else -> Center
            }
        }

        val arr = this.split(" ")
        return when {
            arr.size >= 2 -> SlideAlign(stringToAlign(arr[0]), stringToAlign(arr[1]))
            arr.size == 1 -> SlideAlign(stringToAlign(arr[0]), Center)
            else -> SlideAlign(Center, Center)
        }
    }
}