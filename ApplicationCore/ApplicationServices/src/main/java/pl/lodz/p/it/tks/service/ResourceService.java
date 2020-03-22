package pl.lodz.p.it.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.tks.ports.ResourcePort;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;

import java.util.List;

@Service
public class ResourceService {

    private ResourcePort resources;

    @Autowired
    public ResourceService(ResourcePort resources) {
        this.resources = resources;
    }

    public boolean addResource(Resource resource) {
        return (resources.addResource(resource) == null);
    }

    public void deleteResource(String id) {
        resources.deleteResource(id);
    }

    public List<Resource> getAllResources() {
        return resources.getAll();
    }

    public Resource getResource(String id) {
        return resources.get(id);
    }

    public List<Table> getAllTables() {
        return resources.getAllTables();
    }

    public List<BallRoom> getAllBallRoom() {
        return resources.getAllBallRooms();
    }

    public void updateResource(String id, Resource resource) {
        resources.update(id, resource);
    }
}
