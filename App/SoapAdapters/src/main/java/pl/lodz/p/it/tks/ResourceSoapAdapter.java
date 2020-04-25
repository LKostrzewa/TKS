package pl.lodz.p.it.tks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.service.ResourceService;

@Component
public class ResourceSoapAdapter {

    private ResourceService resourceService;

    @Autowired
    public ResourceSoapAdapter(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
