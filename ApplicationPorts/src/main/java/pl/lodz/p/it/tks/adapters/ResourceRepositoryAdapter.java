package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.ResourceConverter;
import pl.lodz.p.it.tks.data.BallRoomEnt;
import pl.lodz.p.it.tks.data.ResourceEnt;
import pl.lodz.p.it.tks.data.TableEnt;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;
import pl.lodz.p.it.tks.ports.*;
import pl.lodz.p.it.tks.repository.ResourceRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceRepositoryAdapter implements AddResourcePort, DeleteResourcePort, GetResourcesPort, UpdateResourcePort {

    private ResourceRepository repository;
    private ResourceConverter converter;

    @Autowired
    public ResourceRepositoryAdapter(ResourceRepository repository) {
        this.repository = repository;
        converter = new ResourceConverter();
    }

    @Override
    public boolean addResource(Resource resource){
        ResourceEnt resourceEnt = converter.convertResource(resource);
        return repository.add(resourceEnt.getId(), resourceEnt) == null;
    }

    @Override
    public void deleteResource(String id) {
        repository.delete(id);
    }

    @Override
    public Resource getResource(String id){
        if(repository.get(id) instanceof TableEnt)
            return converter.convertTableEnt((TableEnt)repository.get(id));
        else return converter.convertBallRoomEnt((BallRoomEnt)repository.get(id));
    }

    @Override
    public List<Resource> getAllResources(){
        List<Resource> resources = new ArrayList<>();
        for(ResourceEnt resourceEnt : repository.getAll()){
            resources.add(getResource(resourceEnt.getId()));
        }
        return resources;
    }

    @Override
    public List<Table> getAllTables(){
        List<Table> tables = new ArrayList<>();
        for (TableEnt tableEnt : repository.getAllTableEnts()){
            tables.add(converter.convertTableEnt(tableEnt));
        }
        return tables;
    }

    @Override
    public List<BallRoom> getAllBallRooms(){
        List<BallRoom> ballRooms = new ArrayList<>();
        for(BallRoomEnt ballRoomEnt : repository.getAllBallRoomEnts()){
            ballRooms.add(converter.convertBallRoomEnt(ballRoomEnt));
        }
        return ballRooms;
    }

    @Override
    public void updateResource(String id, Resource resource){
        repository.update(id, converter.convertResource(resource));
    }
}
