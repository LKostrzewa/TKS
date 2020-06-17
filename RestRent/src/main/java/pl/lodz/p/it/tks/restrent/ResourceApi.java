package pl.lodz.p.it.tks.restrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.tks.dto.BallRoomDTO;
import pl.lodz.p.it.tks.dto.ResourceDTO;
import pl.lodz.p.it.tks.dto.TableDTO;
import pl.lodz.p.it.tks.useCases.resourceUseCase.AddResourceUseCase;
import pl.lodz.p.it.tks.useCases.resourceUseCase.DeleteResourceUseCase;
import pl.lodz.p.it.tks.useCases.resourceUseCase.UpdateResourceUseCase;
import pl.lodz.p.it.tks.useCases.resourceUseCase.UtilsResourceUseCase;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceApi {

    private AddResourceUseCase addResourceService;
    private UpdateResourceUseCase updateResourceService;
    private DeleteResourceUseCase deleteResourceService;
    private UtilsResourceUseCase utilsResourceService;

    @Autowired
    public ResourceApi(AddResourceUseCase addResourceService, UpdateResourceUseCase updateResourceService, DeleteResourceUseCase deleteResourceService, UtilsResourceUseCase utilsResourceService) {
        this.addResourceService = addResourceService;
        this.updateResourceService = updateResourceService;
        this.deleteResourceService = deleteResourceService;
        this.utilsResourceService = utilsResourceService;
    }

    private ResponseEntity addResource(ResourceDTO resource, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            if (addResourceService.addResource(resource)) {
                return new ResponseEntity(HttpStatus.OK);
            }
            else return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add-table")
    public ResponseEntity addTable(@Valid @RequestBody TableDTO resource, BindingResult bindingResult) {
        return addResource(resource, bindingResult);
    }

    @PostMapping("/add-room")
    public ResponseEntity addBallRoom(@Valid @RequestBody BallRoomDTO resource, BindingResult bindingResult) {
        return addResource(resource, bindingResult);
    }

    @GetMapping
    public List<ResourceDTO> getAllResource() {
        return utilsResourceService.getAllResources();
    }

    @GetMapping("/all-tables")
    public List<TableDTO> showAllTables() {
        return utilsResourceService.getAllTables();
    }

    @GetMapping("/all-rooms")
    public List<BallRoomDTO> getAllRooms() {
        return utilsResourceService.getAllBallRoom();
    }

    @GetMapping("/get-resource/{id}")
    public ResourceDTO getResource(@PathVariable int id) {
        return utilsResourceService.getResource(id);
    }

    @DeleteMapping("/delete-resource/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable int id) {
        deleteResourceService.deleteResource(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity updateResource(ResourceDTO resource, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            updateResourceService.updateResource(resource.getId(), resource);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update-table")
    public ResponseEntity updateTable(@Valid @RequestBody TableDTO resource, BindingResult bindingResult) {
        return updateResource(resource, bindingResult);
    }

    @PutMapping("/update-room")
    public ResponseEntity updateBallRoom(@Valid @RequestBody BallRoomDTO resource, BindingResult bindingResult) {
        return updateResource(resource, bindingResult);
    }
}
