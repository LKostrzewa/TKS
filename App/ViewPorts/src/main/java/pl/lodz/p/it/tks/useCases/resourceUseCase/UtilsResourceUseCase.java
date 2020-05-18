package pl.lodz.p.it.tks.useCases.resourceUseCase;

import pl.lodz.p.it.tks.dto.BallRoomDTO;
import pl.lodz.p.it.tks.dto.ResourceDTO;
import pl.lodz.p.it.tks.dto.TableDTO;

import java.util.List;

public interface UtilsResourceUseCase {
    List<ResourceDTO> getAllResources();
    ResourceDTO getResource(int id);
    List<TableDTO> getAllTables();
    List<BallRoomDTO> getAllBallRoom();
}
