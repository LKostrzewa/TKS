package pl.lodz.p.it.tks;

import com.baeldung.springsoap.gen.ResourceSOAP;
import org.modelmapper.ModelMapper;
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

    public ResourceSOAP findResource(String id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(resourceService.getResource(id), ResourceSOAP.class);
    }
}
