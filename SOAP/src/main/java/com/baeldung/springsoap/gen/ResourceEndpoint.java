package com.baeldung.springsoap.gen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.lodz.p.it.tks.ResourceSoapAdapter;

@Endpoint
public class ResourceEndpoint {
    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

    //private ResourceRepository resourceRepository;
    private ResourceSoapAdapter resourceSoapAdapter;

    @Autowired
    public ResourceEndpoint(ResourceSoapAdapter resourceSoapAdapter) {
        this.resourceSoapAdapter = resourceSoapAdapter;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getResourceRequest")
    @ResponsePayload
    public GetResourceResponse getResource(@RequestPayload GetResourceRequest request) {
        GetResourceResponse response = new GetResourceResponse();
        response.setResource(resourceSoapAdapter.findResource(request.getId()));

        return response;
    }
}
