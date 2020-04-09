package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.ResourceViewConverter;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;
import pl.lodz.p.it.tks.service.ResourceService;
import pl.lodz.p.it.tks.useCases.resourceUseCase.AddResourceUseCase;
import pl.lodz.p.it.tks.useCases.resourceUseCase.DeleteResourceUseCase;
import pl.lodz.p.it.tks.useCases.resourceUseCase.UpdateResourceUseCase;
import pl.lodz.p.it.tks.useCases.resourceUseCase.UtilsResourceUseCase;

import java.util.List;

@Component
public class ResourceViewAdapter implements AddResourceUseCase, DeleteResourceUseCase, UpdateResourceUseCase, UtilsResourceUseCase {

    private ResourceService resourceService;
    private ResourceViewConverter resourceViewConverter;

    @Autowired
    public ResourceViewAdapter(ResourceService resourceService) {
        this.resourceService = resourceService;
        resourceViewConverter = new ResourceViewConverter();
    }

    @Override
    public boolean addResource(Resource resource) {
        return false;
    }

    @Override
    public void deleteResource(String id) {

    }

    @Override
    public void updateResource(String id, Resource resource) {

    }

    @Override
    public List<Resource> getAllResources() {
        return null;
    }

    @Override
    public Resource getResource(String id) {
        return null;
    }

    @Override
    public List<Table> getAllTables() {
        return null;
    }

    @Override
    public List<BallRoom> getAllBallRoom() {
        return null;
    }
}
