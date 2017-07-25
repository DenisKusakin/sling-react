package com.cathcart93.sling.services;

import com.cathcart93.sling.Controller;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;

@Component
@Service(PropsService.class)
public class PropsService {
    @Reference
    private ComponentManager componentManager;

    @Reference
    private BeanSerializer beanSerializer;

    public String props(SlingHttpServletRequest request) {
        Controller controller = componentManager.getController(request);
        return beanSerializer.convertToMap(controller);
    }
}
