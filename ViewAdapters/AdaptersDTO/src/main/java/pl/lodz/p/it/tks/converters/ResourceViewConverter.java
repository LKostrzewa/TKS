package pl.lodz.p.it.tks.converters;

import pl.lodz.p.it.tks.dto.BallRoomDTO;
import pl.lodz.p.it.tks.dto.ResourceDTO;
import pl.lodz.p.it.tks.dto.TableDTO;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;

public class ResourceViewConverter {

    public Resource convertResourceDTO(ResourceDTO resourceDTO){
        if(resourceDTO instanceof TableDTO){
            TableDTO table = (TableDTO) resourceDTO;
            return new Table(table.getId(), table.getPrice(), table.getNumber(), table.getNumOfPeople());
        }
        else {
            BallRoomDTO ballRoom = (BallRoomDTO) resourceDTO;
            return new BallRoom(ballRoom.getId(), ballRoom.getPrice(), ballRoom.getDescription(), ballRoom.getNumOfRooms());
        }
    }

    public TableDTO convertTable(Table table){
        return new TableDTO(table.getId(), table.getPrice(), table.getNumber(), table.getNumOfPeople());
    }

    public BallRoomDTO convertBallRoom(BallRoom ballRoom){
        return new BallRoomDTO(ballRoom.getId(), ballRoom.getPrice(), ballRoom.getDescription(), ballRoom.getNumOfRooms());
    }
}
