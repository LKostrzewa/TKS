package pl.lodz.p.it.tks;

import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;

import java.util.List;

public interface GetResourcesPort {
    List<Resource> getAllResources();
    Resource getResource(String id);
    List<Table> getAllTables();
    List<BallRoom> getAllBallRoom();
}
