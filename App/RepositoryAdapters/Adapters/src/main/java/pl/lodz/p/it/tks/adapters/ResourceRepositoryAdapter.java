package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.ResourceConverter;
import pl.lodz.p.it.tks.data.BallRoomEnt;
import pl.lodz.p.it.tks.data.ResourceEnt;
import pl.lodz.p.it.tks.data.TableEnt;
import pl.lodz.p.it.tks.db.BallRoomDBRepository;
import pl.lodz.p.it.tks.db.ResourceDBRepository;
import pl.lodz.p.it.tks.db.TableDBRepository;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;
import pl.lodz.p.it.tks.ports.resourcePort.AddResourcePort;
import pl.lodz.p.it.tks.ports.resourcePort.DeleteResourcePort;
import pl.lodz.p.it.tks.ports.resourcePort.GetResourcesPort;
import pl.lodz.p.it.tks.ports.resourcePort.UpdateResourcePort;
import pl.lodz.p.it.tks.repository.ResourceRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceRepositoryAdapter implements AddResourcePort, DeleteResourcePort, GetResourcesPort, UpdateResourcePort {

    private ResourceDBRepository repository;
    private TableDBRepository tableDBRepository;
    private BallRoomDBRepository ballRoomDBRepository;
    private ResourceConverter converter;

    @Autowired
    public ResourceRepositoryAdapter(ResourceDBRepository repository, TableDBRepository tableDBRepository, BallRoomDBRepository ballRoomDBRepository) {
        this.repository = repository;
        this.tableDBRepository = tableDBRepository;
        this.ballRoomDBRepository = ballRoomDBRepository;
        converter = new ResourceConverter();
    }

    @Override
    public boolean addResource(Resource resource){
        ResourceEnt resourceEnt = converter.convertResource(resource);
        if(repository.existsById(resourceEnt.getId())){
            return false;
        } else {
            repository.save(resourceEnt);
            return true;
        }
        //return repository.save(resourceEnt) == null;
    }

    @Override
    public void deleteResource(int id) {
        repository.deleteById(id);
    }

    @Override
    public Resource getResource(int id){
        if(repository.getOne(id) instanceof TableEnt)
            return converter.convertTableEnt((TableEnt)repository.getOne(id));
        else return converter.convertBallRoomEnt((BallRoomEnt)repository.getOne(id));
    }

    @Override
    public List<Resource> getAllResources(){
        List<Resource> resources = new ArrayList<>();
        for(ResourceEnt resourceEnt : repository.findAll()){
            resources.add(getResource(resourceEnt.getId()));
        }
        return resources;
    }

    @Override
    public List<Table> getAllTables(){
        List<Table> tables = new ArrayList<>();
        for (TableEnt tableEnt : tableDBRepository.findAll()){
            tables.add(converter.convertTableEnt(tableEnt));
        }
        return tables;
    }

    @Override
    public List<BallRoom> getAllBallRooms(){
        List<BallRoom> ballRooms = new ArrayList<>();
        for(BallRoomEnt ballRoomEnt : ballRoomDBRepository.findAll()){
            ballRooms.add(converter.convertBallRoomEnt(ballRoomEnt));
        }
        return ballRooms;
    }

    @Override
    public void updateResource(int id, Resource resource){
        if(repository.existsById(id)){
            repository.save(converter.convertResource(resource));
        }
        //repository.update(id, converter.convertResource(resource));
    }
}
