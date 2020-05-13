package pl.lodz.p.it.tks.repository;

import pl.lodz.p.it.tks.data.BallRoomEnt;
import pl.lodz.p.it.tks.data.ResourceEnt;
import pl.lodz.p.it.tks.data.TableEnt;

import java.util.ArrayList;
import java.util.List;

public interface ResoucrceDBRepository extends RepositoryDBTemplate<ResourceEnt> {
    //Tu też nw
    List<TableEnt> getAllTableEnts();
    List<BallRoomEnt> getAllBallRoomEnts();
}
