package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.data.BallRoomEnt;
import pl.lodz.p.it.tks.data.ResourceEnt;
import pl.lodz.p.it.tks.data.TableEnt;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;
import pl.lodz.p.it.tks.ports.ResourcePort;
import pl.lodz.p.it.tks.repository.ResourceRepository;

@Component
public class ResourceRepositoryAdapter implements ResourcePort {

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

    @Override
    public ResourceEnt addResource(Resource resource){
        ResourceEnt resourceEnt = convertResource(resource);
        return repository.add(resourceEnt.getId(), resourceEnt);
    }

    @Override
    public void deleteResource(String id) {
        repository.delete(id);
    }

    public Resource getResource(String id){

    }
}
