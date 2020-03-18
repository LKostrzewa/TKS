package pl.lodz.p.it.tks.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;
import pl.lodz.p.it.tks.service.ResourceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceApi {

    private final ResourceService resourceService;

    @Autowired
    public ResourceApi(ResourceService resourceService) {
        this.resourceService = resourceService;
        resourceService.addResource(new BallRoom("testBallRoom", 10, "JakisTekst", 5));
        resourceService.addResource(new Table("test", 10, 10, 10));
    }

    private ResponseEntity addResource(Resource resource, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            if (resourceService.addResource(resource)) {
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
        return resourceService.getAllResources();
    }

    @GetMapping("/all-tables")
    public List<Table> showAllTables() {
        return resourceService.getAllTables();
    }

    @GetMapping("/all-rooms")
    public List<BallRoom> getAllRooms() {
        return resourceService.getAllBallRoom();
    }

    @GetMapping("/get-resource/{id}")
    public Resource getResource(@PathVariable String id) {
        return resourceService.getResource(id);
    }

    @DeleteMapping("/delete-resource/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable String id) {
        resourceService.deleteResource(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity updateResource(Resource resource, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            resourceService.updateResource(resource.getId(), resource);
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
