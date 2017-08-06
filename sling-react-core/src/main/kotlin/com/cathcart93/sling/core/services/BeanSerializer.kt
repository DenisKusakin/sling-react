package com.cathcart93.sling.core.services

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactControllerSerializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.apache.felix.scr.annotations.Activate
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Service

/**
 * Created by Kusak on 7/16/2017.
 */
@Component
@Service(BeanSerializer::class)
class BeanSerializer {
    private val gson = GsonBuilder()
            .registerTypeHierarchyAdapter(IReactController::class.java, ReactControllerSerializer())
            .create()

    fun convertToMap(bean: Any): String {
        return gson.toJson(bean)
    }
}
