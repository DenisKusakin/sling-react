package com.cathcart93.sling.core.services

import com.cathcart93.sling.core.IReactController
import jdk.nashorn.api.scripting.NashornScriptEngine
import org.apache.commons.io.IOUtils
import org.apache.felix.scr.annotations.Activate
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Reference
import org.apache.felix.scr.annotations.Service
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.*
import org.osgi.service.component.ComponentContext
import org.slf4j.LoggerFactory
import javax.script.ScriptEngineManager
import javax.script.ScriptException
import java.io.IOException
import java.io.InputStream
import java.util.HashMap

/**
 * Created by Kusak on 7/9/2017.
 */
@Component
@Service(React::class)
class React {

    @Reference
    private val resolverFactory: ResourceResolverFactory? = null

    @Reference
    private lateinit var beanSerializer: BeanSerializer

    private var nashornScriptEngine: NashornScriptEngine? = null

    fun renderElement(request: SlingHttpServletRequest): String {
        val controller = request.adaptTo(IReactController::class.java) ?: return "{}"
        val props = beanSerializer.convertToMap(controller)
        return render(props)
    }

    private fun render(element: String): String {
        try {
            val exp = String.format(RENDER_REACT_ELEMENT_TEMPLATE, element)
            val html = nashornScriptEngine!!.eval(exp)
            return html.toString()
        } catch (e: Exception) {
            return "Render Exception: " + e.toString()
        }

    }

    @Activate
    protected fun init(componentContext: ComponentContext) {
        val jsSource = readFile(SOURCE_PATH)
        val jsPolyfill = readFile(POLIFILL_SOURCE_PATH)

        nashornScriptEngine = ScriptEngineManager().getEngineByName("nashorn") as NashornScriptEngine

        if (jsSource == null) {
            LOGGER.error("JS Source is not defined")
        }

        if (jsPolyfill == null) {
            LOGGER.error("JS Polyfill Source is not defined")
        }

        try {
            nashornScriptEngine!!.eval(jsPolyfill)
            nashornScriptEngine!!.eval(jsSource)
        } catch (e: ScriptException) {
            LOGGER.error("Script Error: {}", e.toString())
        }

    }

    private fun readFile(path: String): String? {
        val auth = HashMap<String, Any>()
        auth.put(ResourceResolverFactory.SUBSERVICE, "reader")
        var resourceResolver: ResourceResolver? = null
        var jsSource: String? = null
        try {
            resourceResolver = resolverFactory!!.getServiceResourceResolver(auth)
        } catch (e: LoginException) {
            LOGGER.error("Login Exception: {}", e.toString())
            return null
        }

        val sourceResource = resourceResolver.getResource(path)
        try {
            jsSource = IOUtils.toString(sourceResource!!.adaptTo(InputStream::class.java)!!, "UTF-8")
        } catch (e: IOException) {
            LOGGER.error("File does not exist: {}", path)
        }

        return jsSource
    }

    companion object {

        private val SOURCE_PATH = "/etc/react-clientlibs/server.js"
        private val POLIFILL_SOURCE_PATH = "/etc/react-clientlibs/nashorn-polyfill.js"
        private val RENDER_REACT_ELEMENT_TEMPLATE = "renderElement(%s)"

        private val LOGGER = LoggerFactory.getLogger(React::class.java)
    }
}
