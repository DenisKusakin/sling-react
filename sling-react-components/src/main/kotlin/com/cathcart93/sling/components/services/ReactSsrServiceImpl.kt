package com.cathcart93.sling.components.services

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import org.apache.commons.io.IOUtils
import org.apache.felix.scr.annotations.Activate
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Reference
import org.apache.felix.scr.annotations.Service
import org.apache.sling.api.resource.LoginException
import org.apache.sling.api.resource.ResourceResolver
import org.apache.sling.api.resource.ResourceResolverFactory
import org.osgi.service.component.ComponentContext
import org.slf4j.LoggerFactory
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

/**
 * @author Denis_Kusakin. 8/21/2018.
 */
@Component(immediate = true)
@Service(ReactSsrService::class)
class ReactSsrServiceImpl : ReactSsrService {
//    @Reference
//    private lateinit var resolverFactory: ResourceResolverFactory

    private lateinit var enginesCache: Cache<String, ScriptEngine>

    override fun renderToHtmlString(resourceResolver: ResourceResolver, pathToJsFile: String, reactElementJson: String): String {
        val engine = getScriptEngine(resourceResolver, pathToJsFile)
        val exp = "renderReactElement($reactElementJson)"
        return engine.eval(exp) as String
    }

    @Activate
    private fun init(componentContext: ComponentContext) {
        enginesCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .build<String, ScriptEngine>()
    }

    private fun getScriptEngine(resourceResolver: ResourceResolver, scriptPath: String): ScriptEngine {

        return enginesCache.get(scriptPath) {
            val polyfillSource = readFile(resourceResolver, "/etc/aem-poc-clientlibs/nashorn-polyfill.js")
            val jsSource = readFile(resourceResolver, scriptPath)
                    ?: throw FileNotFoundException("JS Source not found: $scriptPath")
            val nashornScriptEngine = ScriptEngineManager().getEngineByName("nashorn")

            try {
                nashornScriptEngine.eval(polyfillSource)
                nashornScriptEngine.eval(jsSource)
            } catch (e: Exception) {
                LOGGER.error("Script Error: {}", e.toString())
            }
            nashornScriptEngine
        }

    }

    private fun readFile(resourceResolver: ResourceResolver, path: String): String? {
//        val auth = HashMap<String, Any>()
//        auth.put(ResourceResolverFactory.SUBSERVICE, "reader")
//        var resourceResolver: ResourceResolver? = null
        var jsSource: String? = null
//        try {
//            resourceResolver = resolverFactory.getServiceResourceResolver(auth)
//        } catch (e: LoginException) {
//            LOGGER.error("Login Exception: {}", e.toString())
//            return null
//        }

        val sourceResource = resourceResolver.getResource(path) ?: return null
        try {
            jsSource = IOUtils.toString(sourceResource.adaptTo(InputStream::class.java), "UTF-8")
        } catch (e: IOException) {
            LOGGER.error("File does not exist: {}", path)
        } finally {
//            resourceResolver.close()
        }

        return jsSource
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ReactSsrServiceImpl::class.java)
    }
}