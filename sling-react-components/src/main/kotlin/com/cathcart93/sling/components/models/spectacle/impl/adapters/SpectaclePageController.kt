package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject

/**
 * Created by Kusak on 7/15/2017.
 */
@Model(
        adaptables = [Resource::class],
        adapters = [ReactModel::class],
        resourceType = ["sling-spectacle/client-page"]
)
class SpectaclePageController : ReactModel {

    @SlingObject
    private lateinit var resource: Resource

    private val contentNodeName = "content"

    override fun render(context: RenderContext): ReactElement {
        val isPreviewMode = !context.isEditMode
        val content = resource.getChild(contentNodeName)?.adaptTo(ReactModel::class.java)?.render(context)
        //val container = resource.adaptTo(DeckModel::class.java)?.render(context)
        return if (!isPreviewMode) ReactElement("SpectacleAuthorRoot", mapOf(
                "url" to "${resource.path}.json".toReactProp(),
                "content" to content!!
        )) else content!!
    }
}