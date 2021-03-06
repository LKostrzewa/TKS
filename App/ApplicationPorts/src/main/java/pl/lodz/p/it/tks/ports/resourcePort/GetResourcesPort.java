package pl.lodz.p.it.tks.ports.resourcePort;

import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;

import java.util.List;

public interface GetResourcesPort {
    Resource getResource(int id);
    List<Resource> getAllResources();
    List<Table> getAllTables();
    List<BallRoom> getAllBallRooms();
}
