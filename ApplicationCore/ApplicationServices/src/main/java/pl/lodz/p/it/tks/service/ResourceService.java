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
import pl.lodz.p.it.tks.useCases.resourceUseCase.AddResourceUseCase;
import pl.lodz.p.it.tks.useCases.resourceUseCase.DeleteResourceUseCase;
import pl.lodz.p.it.tks.useCases.resourceUseCase.UpdateResourceUseCase;
import pl.lodz.p.it.tks.useCases.resourceUseCase.UtilsResourceUseCase;

import java.util.List;

@Service
public class ResourceService implements AddResourceUseCase, UpdateResourceUseCase, DeleteResourceUseCase, UtilsResourceUseCase {

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

    @Override
    public boolean addResource(Resource resource) {
        return (addResourcePort.addResource(resource));
    }

    @Override
    public void deleteResource(String id) {
        deleteResourcePort.deleteResource(id);
    }

    @Override
    public List<Resource> getAllResources() {
        return getResourcesPort.getAllResources();
    }

    @Override
    public Resource getResource(String id) {
        return getResourcesPort.getResource(id);
    }

    @Override
    public List<Table> getAllTables() {
        return getResourcesPort.getAllTables();
    }

    @Override
    public List<BallRoom> getAllBallRoom() {
        return getResourcesPort.getAllBallRooms();
    }

    @Override
    public void updateResource(String id, Resource resource) {
        updateResourcePort.updateResource(id, resource);
    }
}
