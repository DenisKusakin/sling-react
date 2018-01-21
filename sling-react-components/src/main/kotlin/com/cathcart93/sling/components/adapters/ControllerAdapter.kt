package com.cathcart93.sling.components.adapters

import com.cathcart93.sling.core.IReactController
import com.cathcart93.sling.core.ReactController
import com.cathcart93.sling.components.getComponentName
import org.apache.felix.scr.annotations.*
import org.apache.felix.scr.annotations.Properties
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.adapter.AdapterFactory
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.factory.ModelFactory
import org.osgi.framework.Bundle
import org.osgi.service.component.ComponentContext
import org.slf4j.LoggerFactory
import java.net.URL
import java.util.stream.Collectors


@Component(immediate = true)
@Service(AdapterFactory::class)
@Properties(
        Property(name = "adaptables", value = ["org.apache.sling.api.resource.Resource", "org.apache.sling.api.SlingHttpServletRequest"]),
        Property(name = "adapters", value = ["com.cathcart93.sling.core.IReactController"])
)
class ControllerAdapter : AdapterFactory {
    private val LOGGER = LoggerFactory.getLogger(ControllerAdapter::class.java)

    @Reference
    lateinit var modelFactory: ModelFactory

    lateinit var classes: List<Class<Any>>

    override fun <AdapterType> getAdapter(adaptable: Any, aClass: Class<AdapterType>): AdapterType? {
        if (aClass !== IReactController::class.java) {
            return null
        }
        val componentName = getComponentName(adaptable)
        if (adaptable is SlingHttpServletRequest || adaptable is Resource) {
            val controllerClass = classes
                    .findLast { it.getAnnotation(ReactController::class.java).componentName == componentName }
            if (controllerClass !== null) {
                if (modelFactory.canCreateFromAdaptable(adaptable, controllerClass)) {
                    val controllerInstance = modelFactory.createModel(adaptable, controllerClass) as IReactController
                    return controllerInstance as AdapterType?
                } else {
                    LOGGER.error("Controller class found but it can't be instantiated")
                }
            } else {
                val res = (adaptable as? SlingHttpServletRequest)?.resource ?: adaptable as Resource
                LOGGER.error("Controller class not found, component: {}, resource: {}", componentName, res.path)
            }
        }
        return null
    }

    protected fun activate(context: ComponentContext) {
        val bundle: Bundle? = context.bundleContext.bundle
        if (bundle != null) {
            classes = bundle
                    .findEntries("/com/cathcart93/sling/components", "*.class", true)
                    .toList()
                    .map {
                        val url = it as URL
                        val f = url.getFile()
                        val cn = f.substring(1, f.length - ".class".length)
                        cn.replace('/', '.')
                    }
                    .map {
                        bundle.loadClass(it)
                    }
                    .filter { it.isAnnotationPresent(ReactController::class.java) }
        }

        // TODO: Do something on activation
    }

    private fun getComponentName(adaptable: Any): String? {
        if (adaptable is SlingHttpServletRequest) {
            return adaptable.getComponentName()
        } else if (adaptable is Resource) {
            return adaptable.getComponentName()
        }

        return null
    }
}
