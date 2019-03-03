package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.toReactProp
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject

/**
 * Created by Kusak on 7/15/2017.
 */
@Model(
        adaptables = [Resource::class],
        adapters = [ReactModel::class],
        resourceType = ["spectacle/meduza-spectacle"]
)
class MeduzaSpectaclePageController : ReactModel {

    @SlingObject
    private lateinit var resource: Resource

    override fun render(context: RenderContext): ReactElement {
        val container = resource.adaptTo(MeduzaDeckModel::class.java)?.render(context)
        return if (context.isEditMode) ReactElement("SpectacleAuthorRoot", mapOf(
                "renderUrl" to "${resource.path}.json".toReactProp(),
                "content" to container!!
        )) else container!!
    }
}