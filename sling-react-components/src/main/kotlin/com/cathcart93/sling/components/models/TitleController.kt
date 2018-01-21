package com.cathcart93.sling.components.models

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.core.ReactProp
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue
import javax.annotation.PostConstruct

/**
 * Created by Kusak on 7/15/2017.
 */
@Model(adaptables = [(Resource::class)])
@ReactController(componentName = "Title")
class TitleController : IReactController {

    @ValueMapValue(name = "title", optional = true)
    @ReactProp
    private lateinit var title: String

    @SlingObject
    private lateinit var resource: Resource

    @ReactProp("__dialog")
    private lateinit var dialog: Dialog

    @PostConstruct
    fun init() {
        dialog = Dialog(value = title, path = resource.path, name = "title")
    }

    class Dialog(val value: String, val path: String, val name: String) {
        val props = arrayOf(DialogProperty("Title", value, path, name))
    }

    data class DialogProperty(val title: String, val value: String, val path: String, val name: String)

}
