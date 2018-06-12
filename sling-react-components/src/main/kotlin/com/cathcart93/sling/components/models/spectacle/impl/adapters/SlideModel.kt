package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.components.models.spectacle.impl.builder.*
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
class SlideModel : ReactModel, BaseModel() {
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

    private val components = listOf(
            ContainerComponent(title = "Heading", description = "Heading component", resourceType = Constants.HEADING),
            ContainerComponent(title = "Text", description = "Text component", resourceType = Constants.TEXT),
            ContainerComponent(title = "Link", description = "Link component", resourceType = Constants.LINK),
            ContainerComponent(title = "Image", description = "Image component", resourceType = Constants.IMAGE),
            ContainerComponent(title = "Block Quote", description = "BlockQuote component", resourceType = Constants.BLOCK_QUOTE),
            ContainerComponent(title = "Code", description = "Code component", resourceType = Constants.CODE_PANE)
    )

    override fun toReact(isEditMode: Boolean): SpectacleTag {
        val children = resource.children
                .mapNotNull { it.adaptTo(ReactModel::class.java) }
                .map { it.toReact(isEditMode) }
        val deckPropertiesButton = resource.parent?.adaptTo(DeckModel::class.java)!!.propertiesButton()
        val systemButtons by lazy {
            SystemButtonsContainer(
                    EditModeToggler,
                    DeleteSlideButton(resource.path),
                    propertiesButton("Slide Properties", resource.path) {
                        select(
                                name = "textColor",
                                title = "Text Color",
                                value = textColor ?: "",
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
                                value = bgColor ?: "",
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
                                value = transition ?: ""
                        )
                        text(
                                name = "transitionDuration",
                                title = "Transition Duration",
                                value = transitionDuration?.toString() ?: ""
                        )
                        text(
                                name = "align",
                                title = "Align",
                                value = align ?: ""
                        )
                        multilineText(
                                name = "notes",
                                title = "Notes",
                                value = notes ?: ""
                        )
                        text(
                                name = "id",
                                title = "Slide ID",
                                value = id ?: ""
                        )
                    },
                    deckPropertiesButton
            )
        }

        return slide {
            transition = this@SlideModel.transition?.toSlideTransition()
            transitionDuration = this@SlideModel.transitionDuration
            align = this@SlideModel.align?.toSlideAlign()
            id = this@SlideModel.id
            notes = this@SlideModel.notes
            bgColor = this@SlideModel.bgColor
            if (isEditMode) {
                comp(Container(children = children, components = components, resourcePath = "${resource.path}/", moveInfo = resource.toMoveInfo()))
                comp(systemButtons)
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

    private fun Resource.toMoveInfo(): List<MoveInfoItem> {
        return this.children.map { MoveInfoItem(name = it.name, url = it.path) }
    }
}