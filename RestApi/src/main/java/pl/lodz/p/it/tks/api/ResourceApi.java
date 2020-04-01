package pl.lodz.p.it.tks.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;
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
        addResourceService.addResource(new BallRoom("testBallRoom", 10, "JakisTekst", 5));
        addResourceService.addResource(new Table("test", 10, 10, 10));
    }

    private ResponseEntity addResource(Resource resource, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            if (addResourceService.addResource(resource)) {
                return new ResponseEntity(HttpStatus.OK);
            }
            else return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add-table")
    public ResponseEntity addTable(@Valid @RequestBody Table resource, BindingResult bindingResult) {
        return addResource(resource, bindingResult);
    }

    @PostMapping("/add-room")
    public ResponseEntity addBallRoom(@Valid @RequestBody BallRoom resource, BindingResult bindingResult) {
        return addResource(resource, bindingResult);
    }

    @GetMapping
    public List<Resource> getAllResource() {
        return utilsResourceService.getAllResources();
    }

    @GetMapping("/all-tables")
    public List<Table> showAllTables() {
        return utilsResourceService.getAllTables();
    }

    @GetMapping("/all-rooms")
    public List<BallRoom> getAllRooms() {
        return utilsResourceService.getAllBallRoom();
    }

    @GetMapping("/get-resource/{id}")
    public Resource getResource(@PathVariable String id) {
        return utilsResourceService.getResource(id);
    }

    @DeleteMapping("/delete-resource/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable String id) {
        deleteResourceService.deleteResource(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity updateResource(Resource resource, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            updateResourceService.updateResource(resource.getId(), resource);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update-table")
    public ResponseEntity updateTable(@Valid @RequestBody Table resource, BindingResult bindingResult) {
        return updateResource(resource, bindingResult);
    }

    @PutMapping("/update-room")
    public ResponseEntity updateBallRoom(@Valid @RequestBody BallRoom resource, BindingResult bindingResult) {
        return updateResource(resource, bindingResult);
    }
}
