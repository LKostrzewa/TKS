package pl.lodz.p.it.tks.repository;

import org.springframework.stereotype.Repository;
import pl.lodz.p.it.tks.data.BallRoomEnt;
import pl.lodz.p.it.tks.data.ResourceEnt;
import pl.lodz.p.it.tks.data.TableEnt;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ResourceRepository extends RepositoryTemplate<ResourceEnt> {

    public List<TableEnt> getAllTableEnts(){
        List<TableEnt> list = new ArrayList<>();
        getAll().stream().filter(t -> t instanceof TableEnt)
                .forEach(t -> list.add((TableEnt) t));
        return list;
    }

    public List<BallRoomEnt> getAllBallRoomEnts(){
        List<BallRoomEnt> list = new ArrayList<>();
        getAll().stream().filter(b -> b instanceof BallRoomEnt)
                .forEach(b -> list.add((BallRoomEnt) b));
        return list;
    }
}
