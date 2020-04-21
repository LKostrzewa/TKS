package pl.lodz.p.it.tks.soap.factory;

import pl.lodz.p.it.tks.soap.GetResourceRequest;
import pl.lodz.p.it.tks.soap.GetResourceResponse;
import pl.lodz.p.it.tks.soap.model.Resource;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    public ObjectFactory() {

    }

    public GetResourceRequest createGetResourceRequest(){
        return new GetResourceRequest();
    }

    public GetResourceResponse createGetResourceResponse() {
        return new GetResourceResponse();
    }

    public Resource createResource(){
        return new Resource();
    }
}
