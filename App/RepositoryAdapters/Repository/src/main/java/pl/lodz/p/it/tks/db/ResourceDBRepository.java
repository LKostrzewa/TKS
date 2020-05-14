package pl.lodz.p.it.tks.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.it.tks.data.BallRoomEnt;
import pl.lodz.p.it.tks.data.ResourceEnt;
import pl.lodz.p.it.tks.data.TableEnt;

import java.util.List;

public interface ResourceDBRepository extends JpaRepository<ResourceEnt, String> {
    //Tu też nw
    List<TableEnt> getAllTableEnts();
    List<BallRoomEnt> getAllBallRoomEnts();
}
