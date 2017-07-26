package com.cathcart93.sling.models;

import com.cathcart93.sling.BaseController;
import com.google.gson.annotations.Expose;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Model(adaptables = Resource.class)
public class AccordionController extends BaseController{
    @Expose
    private String __type = "Accordion";

    @Expose
    private List<AccordionItemModel> items;

    @Override
    @PostConstruct
    public void init(){
        items = StreamSupport.stream(resource.getChildren().spliterator(), false)
                .map(item -> {
                    AccordionItemModel itemModel = item.adaptTo(AccordionItemModel.class);
                    itemModel.init();
                    return itemModel;
                })
                .collect(Collectors.toList());
    }
}
