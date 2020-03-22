package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    @Autowired
    public ResourceRepositoryAdapter(ResourceRepository repository) {
        this.repository = repository;
    }

    private ResourceEnt convertResource(Resource resource){
        if(resource instanceof Table){
            Table table = (Table) resource;
            TableEnt tableEnt = new TableEnt();
            tableEnt.setNumber(table.getNumber());
            tableEnt.setNumOfPeople(table.getNumOfPeople());
            tableEnt.setId(table.getId());
            tableEnt.setPrice(table.getPrice());
            return  tableEnt;
        }
        else {
            BallRoom ballRoom = (BallRoom) resource;
            BallRoomEnt ballRoomEnt = new BallRoomEnt();
            ballRoomEnt.setDescription(ballRoom.getDescription());
            ballRoomEnt.setNumOfRooms(ballRoom.getNumOfRooms());
            ballRoomEnt.setId(ballRoom.getId());
            ballRoomEnt.setPrice(ballRoom.getPrice());
            return ballRoomEnt;
        }
    }

    private Table convertTableEnt(TableEnt tableEnt){
        Table table = new Table();
        table.setNumber(tableEnt.getNumber());
        table.setNumOfPeople(tableEnt.getNumOfPeople());
        table.setId(tableEnt.getId());
        table.setPrice(tableEnt.getPrice());
        return table;
    }

    private BallRoom convertBallRoomEnt(BallRoomEnt ballRoomEnt){
        BallRoom ballRoom = new BallRoom();
        ballRoom.setDescription(ballRoomEnt.getDescription());
        ballRoom.setNumOfRooms(ballRoomEnt.getNumOfRooms());
        ballRoom.setId(ballRoomEnt.getId());
        ballRoom.setPrice(ballRoomEnt.getPrice());
        return ballRoom;
    }

    @Override
    public ResourceEnt addResource(Resource resource){
        ResourceEnt resourceEnt = convertResource(resource);
        return repository.add(resourceEnt.getId(), resourceEnt);
    }

    @Override
    public void deleteResource(String id) {
        repository.delete(id);
    }

    @Override
    public Resource getResource(String id){
        if(repository.get(id) instanceof TableEnt)
            return convertTableEnt((TableEnt)repository.get(id));
        else return convertBallRoomEnt((BallRoomEnt)repository.get(id));
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
            tables.add(convertTableEnt(tableEnt));
        }
        return tables;
    }

    @Override
    public List<BallRoom> getAllBallRooms(){
        List<BallRoom> ballRooms = new ArrayList<>();
        for(BallRoomEnt ballRoomEnt : repository.getAllBallRoomEnts()){
            ballRooms.add(convertBallRoomEnt(ballRoomEnt));
        }
        return ballRooms;
    }

    @Override
    public void updateResource(String id, Resource resource){
        repository.update(id, convertResource(resource));
    }
}
