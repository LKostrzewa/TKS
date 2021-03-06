package pl.lodz.p.it.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;
import pl.lodz.p.it.tks.ports.resourcePort.AddResourcePort;
import pl.lodz.p.it.tks.ports.resourcePort.DeleteResourcePort;
import pl.lodz.p.it.tks.ports.resourcePort.GetResourcesPort;
import pl.lodz.p.it.tks.ports.resourcePort.UpdateResourcePort;

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
        //addResourcePort.addResource(new BallRoom("testBallRoom", 10, "JakisTekst", 5));
        //addResourcePort.addResource(new Table("test", 10, 10, 10));
        //addResourcePort.addResource(new Table("testTable", 56.12, 21, 4));
    }

    public boolean addResource(Resource resource) {
        return (addResourcePort.addResource(resource));
    }

    public void deleteResource(int id) {
        deleteResourcePort.deleteResource(id);
    }

    public List<Resource> getAllResources() {
        return getResourcesPort.getAllResources();
    }

    public Resource getResource(int id) {
        return getResourcesPort.getResource(id);
    }

    public List<Table> getAllTables() {
        return getResourcesPort.getAllTables();
    }

    public List<BallRoom> getAllBallRoom() {
        return getResourcesPort.getAllBallRooms();
    }

    public void updateResource(int id, Resource resource) {
        updateResourcePort.updateResource(id, resource);
    }
}
