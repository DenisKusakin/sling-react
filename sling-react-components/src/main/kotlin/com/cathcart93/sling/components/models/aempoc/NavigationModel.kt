package com.cathcart93.sling.components.models.aempoc

import com.cathcart93.sling.components.models.spectacle.impl.builder.aempoc.*
import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactElement
import org.apache.sling.api.resource.Resource
import org.apache.sling.api.resource.ValueMap
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = [NavigationModel::class]
)
class NavigationModel : AEMReactModel {
    @SlingObject
    private lateinit var resource: Resource

    override fun toReact(): ReactElement {
        val links = resource.parent?.children
                ?.filter { "cq:Page" == (it.adaptTo(ValueMap::class.java)!!)["jcr:primaryType"] }
                ?.map {
                    val props = it.getChild("jcr:content")!!.adaptTo(ValueMap::class.java)!!
                    Link(href = "${it.path}.html", title = props.get("jcr:title", ""))
                } ?: emptyList()
        return Navigation(links).toReactElement()
    }

}