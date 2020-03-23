package pl.lodz.p.it.tks.converters;

import pl.lodz.p.it.tks.data.BallRoomEnt;
import pl.lodz.p.it.tks.data.ResourceEnt;
import pl.lodz.p.it.tks.data.TableEnt;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;

public class ResourceConverter {

    public ResourceEnt convertResource(Resource resource){
        if(resource instanceof Table){
            Table table = (Table) resource;
            return new TableEnt(table.getId(), table.getPrice(), table.getNumber(), table.getNumOfPeople());
        }
        else {
            BallRoom ballRoom = (BallRoom) resource;
            return new BallRoomEnt(ballRoom.getId(), ballRoom.getPrice(), ballRoom.getDescription(), ballRoom.getNumOfRooms());
        }
    }

    public Table convertTableEnt(TableEnt tableEnt){
        return new Table(tableEnt.getId(), tableEnt.getPrice(), tableEnt.getNumber(), tableEnt.getNumOfPeople());
    }

    public BallRoom convertBallRoomEnt(BallRoomEnt ballRoomEnt){
        return new BallRoom(ballRoomEnt.getId(), ballRoomEnt.getPrice(), ballRoomEnt.getDescription(), ballRoomEnt.getNumOfRooms());
    }
}
