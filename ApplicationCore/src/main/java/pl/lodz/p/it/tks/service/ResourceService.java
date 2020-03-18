package pl.lodz.p.it.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;
import pl.lodz.p.it.tks.repository.ResourceRepository;

import java.util.List;

@Service
public class ResourceService {

    private ResourceRepository resources;

    @Autowired
    public ResourceService(ResourceRepository resources) {
        this.resources = resources;
    }

    public boolean addResource(Resource resource) {
        return (resources.add(resource.getId(), resource) == null);
    }

    public void deleteResource(String id) {
        resources.delete(id);
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
