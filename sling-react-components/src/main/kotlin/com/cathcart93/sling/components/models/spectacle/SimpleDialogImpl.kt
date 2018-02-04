package com.cathcart93.sling.components.models.spectacle

import org.apache.sling.api.resource.Resource

class SimpleDialogImpl(resource: Resource, propsMap: Map<String, String>) : SimpleDialog {
    private val props: List<SimpleDialog.Property>
    override val path: String = resource.path

    init {
        props = propsMap.keys.map {
            PropertyImpl(
                    name = it,
                    value = resource.valueMap.get(it, String::class.java) ?: "",
                    title = propsMap[it]!!
            )
        }
    }

    override fun getProps(): List<SimpleDialog.Property> {
        return props
    }

    class PropertyImpl(
            override val name: String,
            override val value: String,
            override val title: String
    ) : SimpleDialog.Property
}