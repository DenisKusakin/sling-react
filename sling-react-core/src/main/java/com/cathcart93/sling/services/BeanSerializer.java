package com.cathcart93.sling.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import java.util.Map;

/**
 * Created by Kusak on 7/16/2017.
 */
@Component
@Service(BeanSerializer.class)
public class BeanSerializer {
    //private ObjectMapper objectMapper;
    private Gson gson;

    @Activate
    protected void activate() {
//        SerializerFactory serializerFactory = BeanSerializerFactory
//                .instance;
//
//        objectMapper = new ObjectMapper();
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
//        objectMapper.setSerializerFactory(serializerFactory);
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    public String convertToMap(Object bean) {
        return gson.toJson(bean);//objectMapper.convertValue(bean, Map.class);
    }
}
