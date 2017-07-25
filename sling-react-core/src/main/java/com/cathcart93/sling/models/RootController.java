package com.cathcart93.sling.models;

import com.cathcart93.sling.BaseController;
import com.cathcart93.sling.Controller;
import com.cathcart93.sling.services.ComponentManager;
import com.google.gson.annotations.Expose;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Kusak on 7/15/2017.
 */
@Model(adaptables = Resource.class)
public class RootController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);

    @Expose
    private String __type = "Root";

    @Expose
    @ValueMapValue(name = "title", optional = true)
    private String title;

    @Expose
    //private List<Controller> components;
    private Par app;

    @OSGiService
    private ComponentManager componentManager;

    @PostConstruct
    @Override
    public void init() {
        LOGGER.info("Init called");
        app = new Par();

        app.components = StreamSupport.stream(resource.getChildren().spliterator(), false)
                .map(childResource -> componentManager.getController(childResource))
                .collect(Collectors.toList());

    }

    class Par {
        @Expose
        private String __type = "Container";

        @Expose
        private List<Controller> components;
    }
}
