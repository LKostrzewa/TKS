package pl.lodz.p.it.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.tks.ports.*;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;

import java.util.List;

@Service
public class ResourceService {

    private AddResourcePort addResourcePort;
    private UpdateResourcePort updateResourcePort;
    private GetResourcesPort getResourcesPort;
    private DeleteResourcePort deleteResourcePort;

    @Autowired
    public ResourceService(AddResourcePort addResourcePort, UpdateResourcePort updateResourcePort, GetResourcesPort getResourcesPort, DeleteResourcePort deleteResourcePort) {
        this.addResourcePort = addResourcePort;
        this.updateResourcePort = updateResourcePort;
        this.getResourcesPort = getResourcesPort;
        this.deleteResourcePort = deleteResourcePort;
    }

    public boolean addResource(Resource resource) {
        return (addResourcePort.addResource(resource) == null);
    }

    public void deleteResource(String id) {
        deleteResourcePort.deleteResource(id);
    }

    public List<Resource> getAllResources() {
        return getResourcesPort.getAllResources();
    }

    public Resource getResource(String id) {
        return getResourcesPort.getResource(id);
    }

    public List<Table> getAllTables() {
        return getResourcesPort.getAllTables();
    }

    public List<BallRoom> getAllBallRoom() {
        return getResourcesPort.getAllBallRooms();
    }

    public void updateResource(String id, Resource resource) {
        updateResourcePort.updateResource(id, resource);
    }
}
