package pl.lodz.p.it.tks.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.lodz.p.it.tks.soap.GetResourceRequest;
import pl.lodz.p.it.tks.soap.GetResourceResponse;
import pl.lodz.p.it.tks.soap.repository.ResourceRepository;

@Endpoint
public class ResourceEndpoint {
    private ResourceRepository resourceRepository;

    @Autowired
    public ResourceEndpoint(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @PayloadRoot(localPart = "getResourceRequest")
    @ResponsePayload
    public GetResourceResponse getResource(@RequestPayload GetResourceRequest request){
        GetResourceResponse response = new GetResourceResponse();
        response.setResource(resourceRepository.findResource(request.getId()));
        return response;
    }
}
