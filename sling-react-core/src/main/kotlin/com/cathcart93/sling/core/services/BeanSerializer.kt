package com.cathcart93.sling.core.services

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactControllerSerializer
import com.cathcart93.sling.core.measureExecTime
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.apache.felix.scr.annotations.Activate
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Service
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.osgi.service.component.ComponentContext
import org.slf4j.LoggerFactory

/**
 * Created by Kusak on 7/16/2017.
 */
@Component
@Service(BeanSerializer::class)
class BeanSerializer {
    private lateinit var gson: Gson

    @Activate
    private fun init(componentContext: ComponentContext) {
        gson = GsonBuilder()
//                .setExclusionStrategies(
//                        SlingExclusionStrategy(
//                                Resource::class.java, SlingHttpServletRequest::class.java, BeanSerializer::class.java
//                        )
//                )
                .serializeNulls()
                .create()
    }

    fun convertToMap(bean: Any): String {
        val (result, time) = measureExecTime { gson.toJson(bean) }
        LOGGER.trace("Serialization time: {}", time)
        return result
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(React::class.java)
    }
}
