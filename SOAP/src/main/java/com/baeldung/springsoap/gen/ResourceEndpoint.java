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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postBallRoomRequest")
    @ResponsePayload
    public PostBallRoomResponse postBallRoom(@RequestPayload PostBallRoomRequest request) {
        PostBallRoomResponse response = new PostBallRoomResponse();
        BallRoomSOAP ballRoomSOAP = new BallRoomSOAP();
        ballRoomSOAP.setId(request.getId());
        ballRoomSOAP.setPrice(request.getPrice());
        ballRoomSOAP.setDescription(request.getDescription());
        ballRoomSOAP.setNumOfRooms(request.getNumOfRooms());
        response.setId(resourceSoapAdapter.addBallRoom(ballRoomSOAP));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postTableRequest")
    @ResponsePayload
    public PostTableResponse postTable(@RequestPayload PostTableRequest request) {
        PostTableResponse response = new PostTableResponse();
        TableSOAP tableSOAP = new TableSOAP();
        tableSOAP.setId(request.getId());
        tableSOAP.setPrice(request.getPrice());
        tableSOAP.setNumber(request.getNumber());
        tableSOAP.setNumOfPeople(request.getNumOfPeople());
        response.setId(resourceSoapAdapter.addTable(tableSOAP));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTablesRequest")
    @ResponsePayload
    public GetTablesResponse getTables(){
        GetTablesResponse getTablesResponse = new GetTablesResponse();
        getTablesResponse.setTable(resourceSoapAdapter.getTables());
        return getTablesResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteResourceRequest")
    @ResponsePayload
    public DeleteResourceResponse deleteResource(@RequestPayload DeleteResourceRequest request){
        DeleteResourceResponse response = new DeleteResourceResponse();
        response.setInfo(resourceSoapAdapter.deleteResource(request.getId()));
        return response;
    }

}
