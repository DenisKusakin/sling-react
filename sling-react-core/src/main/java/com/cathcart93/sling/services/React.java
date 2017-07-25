package com.cathcart93.sling.services;

import com.cathcart93.sling.ComponentHelper;
import com.cathcart93.sling.Controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.apache.commons.io.IOUtils;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kusak on 7/9/2017.
 */
@Component
@Service(React.class)
public class React {

    private static final String SOURCE_PATH = "/etc/react-clientlibs/server.js";
    private static final String POLIFILL_SOURCE_PATH = "/etc/react-clientlibs/nashorn-polyfill.js";
    private static final String RENDER_REACT_ELEMENT_TEMPLATE = "renderElement(%s)";

    private static final Logger LOGGER = LoggerFactory.getLogger(React.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Reference
    private PropsService propsService;

    private NashornScriptEngine nashornScriptEngine;

    public String renderElement(SlingHttpServletRequest request) {
        String props = propsService.props(request);
        return render(props);
    }

    private String render(@Nonnull String element) {
        try {
            String exp = String.format(RENDER_REACT_ELEMENT_TEMPLATE, element);
            Object html = nashornScriptEngine.eval(exp);
            return html.toString();
        } catch (Exception e) {
            return "Render Exception: " + e.toString();
        }
    }

    @Activate
    protected void init(ComponentContext componentContext) {
        String jsSource = readFile(SOURCE_PATH);
        String jsPolyfill = readFile(POLIFILL_SOURCE_PATH);

        nashornScriptEngine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");

        if (jsSource == null) {
            LOGGER.error("JS Source is not defined");
        }

        if (jsPolyfill == null) {
            LOGGER.error("JS Polyfill Source is not defined");
        }

        try {
            nashornScriptEngine.eval(jsPolyfill);
            nashornScriptEngine.eval(jsSource);
        } catch (ScriptException e) {
            LOGGER.error("Script Error: {}", e.toString());
        }
    }

    private String readFile(String path) {
        Map<String, Object> auth = new HashMap<>();
        auth.put(ResourceResolverFactory.SUBSERVICE, "reader");
        ResourceResolver resourceResolver = null;
        String jsSource = null;
        try {
            resourceResolver = resolverFactory.getServiceResourceResolver(auth);
        } catch (LoginException e) {
            LOGGER.error("Login Exception: {}", e.toString());
            return null;
        }
        Resource sourceResource = resourceResolver.getResource(path);
        try {
            jsSource = IOUtils.toString(sourceResource.adaptTo(InputStream.class), "UTF-8");
        } catch (IOException e) {
            LOGGER.error("File does not exist: {}", path);
        }
        return jsSource;
    }
}
