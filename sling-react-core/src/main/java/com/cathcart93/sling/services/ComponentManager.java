package com.cathcart93.sling.services;

import com.cathcart93.sling.ComponentHelper;
import com.cathcart93.sling.Controller;
import com.cathcart93.sling.models.GreetingController;
import com.cathcart93.sling.models.RootController;
import com.cathcart93.sling.models.SliderController;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kusak on 7/15/2017.
 */
@Component
@Service(ComponentManager.class)
public class ComponentManager {
    private static final Map<String, Class<? extends Controller>> controllerMap = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentManager.class);

    static {
        controllerMap.put("Root", RootController.class);
        controllerMap.put("Greeting", GreetingController.class);
        controllerMap.put("Slider", SliderController.class);
    }

    @Reference
    private ModelFactory modelFactory;

    public Controller getController(SlingHttpServletRequest request) {
        String componentName = ComponentHelper.getComponentName(request);
        Class<Controller> controllerClass = (Class<Controller>) controllerMap.get(componentName);
        Controller controller = null;
        if (modelFactory.canCreateFromAdaptable(request, controllerClass)) {
            controller = modelFactory.createModel(request, controllerClass);
        } else if (modelFactory.canCreateFromAdaptable(request.getResource(), controllerClass)) {
            controller = modelFactory.createModel(request.getResource(), controllerClass);
        }
        if (controller == null) {
            LOGGER.error("Request can not be adapted to Controller");
            return null;
        }
        controller.init();
        return controller;
    }

    public Controller getController(Resource resource) {
        String componentName = ComponentHelper.getComponentName(resource);
        Controller controller = resource.adaptTo(controllerMap.get(componentName));
        if (controller == null) {
            LOGGER.error("Resource can not be adapted to Controller: {}", resource.getPath());
            return null;
        }
        controller.init();
        return controller;
    }
}
