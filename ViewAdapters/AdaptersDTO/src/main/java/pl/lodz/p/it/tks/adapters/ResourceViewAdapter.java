package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.ResourceViewConverter;
import pl.lodz.p.it.tks.dto.BallRoomDTO;
import pl.lodz.p.it.tks.dto.ResourceDTO;
import pl.lodz.p.it.tks.dto.TableDTO;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;
import pl.lodz.p.it.tks.service.ResourceService;
import pl.lodz.p.it.tks.useCases.resourceUseCase.AddResourceUseCase;
import pl.lodz.p.it.tks.useCases.resourceUseCase.DeleteResourceUseCase;
import pl.lodz.p.it.tks.useCases.resourceUseCase.UpdateResourceUseCase;
import pl.lodz.p.it.tks.useCases.resourceUseCase.UtilsResourceUseCase;

import java.util.ArrayList;
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
    public boolean addResource(ResourceDTO resourceDTO) {
        Resource resource = resourceViewConverter.convertResourceDTO(resourceDTO);
        return resourceService.addResource(resource);
    }

    @Override
    public void deleteResource(String id) {
        resourceService.deleteResource(id);
    }

    @Override
    public void updateResource(String id, ResourceDTO resource) {
        resourceService.updateResource(id, resourceViewConverter.convertResourceDTO(resource));
    }

    @Override
    public List<ResourceDTO> getAllResources() {
        List<ResourceDTO> resourceDTOS = new ArrayList<>();
        resourceService.getAllResources().forEach(r -> resourceDTOS.add(getResource(r.getId())));
        return resourceDTOS;
    }

    @Override
    public ResourceDTO getResource(String id) {
        if(resourceService.getResource(id) instanceof Table)
            return resourceViewConverter.convertTable((Table) resourceService.getResource(id));
        else return resourceViewConverter.convertBallRoom((BallRoom)resourceService.getResource(id));
    }

    @Override
    public List<TableDTO> getAllTables() {
        List<TableDTO> tableDTOS = new ArrayList<>();
        resourceService.getAllTables().forEach(r -> tableDTOS.add(resourceViewConverter.convertTable(r)));
        return tableDTOS;
    }

    @Override
    public List<BallRoomDTO> getAllBallRoom() {
        List<BallRoomDTO> ballRoomDTOS = new ArrayList<>();
        resourceService.getAllBallRoom().forEach(r -> ballRoomDTOS.add(resourceViewConverter.convertBallRoom(r)));
        return ballRoomDTOS;
    }
}
