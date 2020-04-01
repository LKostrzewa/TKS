package pl.lodz.p.it.tks.useCases;

import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;

import java.util.List;

public interface ResourceUseCase {
    boolean addResource(Resource resource);
    void deleteResource(String id);
    List<Resource> getAllResources();
    Resource getResource(String id);
    List<Table> getAllTables();
    List<BallRoom> getAllBallRoom();
    void updateResource(String id, Resource resource);
}
