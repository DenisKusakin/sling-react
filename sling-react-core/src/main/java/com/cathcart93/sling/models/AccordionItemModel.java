package com.cathcart93.sling.models;

import com.cathcart93.sling.BaseController;
import com.cathcart93.sling.Controller;
import com.cathcart93.sling.services.ComponentManager;
import com.google.gson.annotations.Expose;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Model(adaptables = Resource.class)
public class AccordionItemModel {

    @Expose
    @ValueMapValue(name = "title")
    private String title;

    @SlingObject
    protected Resource resource;

    @Expose
    private RootController.Par body = new RootController.Par();

    @OSGiService
    private ComponentManager componentManager;

    @PostConstruct
    public void init(){
        //body = componentManager.getController(resource.getChild("body"));
        body.components = StreamSupport.stream(resource.getChildren().spliterator(), false)
                .map(item -> componentManager.getController(item))
                .collect(Collectors.toList());
    }

//    class Par {
//        @Expose
//        private String __type = "Container";
//
//        @Expose
//        private List<Controller> components;
//    }
}
