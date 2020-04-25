package com.baeldung.springsoap.gen;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

//@Component
public class ResourceRepository {

    private static final Map<String, ResourceSOAP> resources = new HashMap<>();

    @PostConstruct
    public void initData() {
        ResourceSOAP resource = new ResourceSOAP();
        resource.setId("1");
        resource.setPrice(15.67);
        ResourceSOAP resource1 = new ResourceSOAP();
        resource1.setId("2");
        resource1.setPrice(61.09);
        resources.put("1", resource);
        resources.put("2", resource1);
    }

    public ResourceSOAP findCountry(String id) {
        return resources.get(id);
    }
}
