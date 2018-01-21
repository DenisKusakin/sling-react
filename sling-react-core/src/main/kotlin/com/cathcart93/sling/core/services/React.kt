package com.cathcart93.sling.core.services

import com.cathcart93.sling.core.measureExecTime
import com.google.common.cache.Cache
import jdk.nashorn.api.scripting.NashornScriptEngine
import org.apache.commons.io.IOUtils
import org.apache.felix.scr.annotations.Activate
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Reference
import org.apache.felix.scr.annotations.Service
import org.apache.sling.api.resource.*
import org.osgi.service.component.ComponentContext
import org.slf4j.LoggerFactory
import java.io.FileNotFoundException
import javax.script.ScriptEngineManager
import java.io.IOException
import java.io.InputStream
import java.util.HashMap
import com.google.common.cache.CacheBuilder


/**
 * Created by Kusak on 7/9/2017.
 */
@Component
@Service(ReactEngine::class)
class React : ReactEngine {

    @Reference
    private lateinit var resolverFactory: ResourceResolverFactory

    private lateinit var enginesCache: Cache<String, NashornScriptEngine>

    override fun render(props: String, scriptPath: String): String {
        val scriptEngine = getScriptEngine(scriptPath)

        val (html, time) = measureExecTime {
            try {
                val exp = String.format(RENDER_REACT_ELEMENT_TEMPLATE, props)
                val html = scriptEngine.eval(exp)
                html.toString()
            } catch (e: Exception) {
                "Render Exception: $e"
            }
        }

        LOGGER.info("Render time: {}", time)

        return html
    }

    private fun getScriptEngine(scriptPath: String): NashornScriptEngine {

        return enginesCache.get(scriptPath, {
            val (engine, time) = measureExecTime {
                val jsPolyfill = readFile(POLYFILL_SOURCE_PATH)
                val jsSource = readFile(scriptPath) ?: throw FileNotFoundException("JS Source not found: $scriptPath")
                val nashornScriptEngine = ScriptEngineManager().getEngineByName("nashorn") as NashornScriptEngine

                if (jsPolyfill == null) {
                    LOGGER.error("JS Polyfill Source is not defined")
                }

                try {
                    if (jsPolyfill != null) {
                        nashornScriptEngine.eval(jsPolyfill)
                    }
                    nashornScriptEngine.eval(jsSource)
                } catch (e: Exception) {
                    LOGGER.error("Script Error: {}", e.toString())
                }

                nashornScriptEngine
            }
            LOGGER.info("JS source parsing time: {}", time)
            engine
        })

    }

    @Activate
    private fun init(componentContext: ComponentContext) {
        enginesCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .build<String, NashornScriptEngine>()
    }

    private fun readFile(path: String): String? {
        val auth = HashMap<String, Any>()
        auth.put(ResourceResolverFactory.SUBSERVICE, "reader")
        var resourceResolver: ResourceResolver? = null
        var jsSource: String? = null
        try {
            resourceResolver = resolverFactory.getServiceResourceResolver(auth)
        } catch (e: LoginException) {
            LOGGER.error("Login Exception: {}", e.toString())
            return null
        }

        val sourceResource = resourceResolver.getResource(path) ?: return null
        try {
            jsSource = IOUtils.toString(sourceResource.adaptTo(InputStream::class.java), "UTF-8")
        } catch (e: IOException) {
            LOGGER.error("File does not exist: {}", path)
        } finally {
            resourceResolver.close()
        }

        return jsSource
    }

    companion object {

        private val POLYFILL_SOURCE_PATH = "/etc/react-clientlibs/nashorn-polyfill.js"
        private val RENDER_REACT_ELEMENT_TEMPLATE = "renderElement(%s)"

        private val LOGGER = LoggerFactory.getLogger(React::class.java)
    }
}
