package pl.lodz.p.it.tks;

import com.baeldung.springsoap.gen.BallRoomSOAP;
import com.baeldung.springsoap.gen.ResourceSOAP;
import com.baeldung.springsoap.gen.TableSOAP;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Table;
import pl.lodz.p.it.tks.service.ResourceService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResourceSoapAdapter {

    private ResourceService resourceService;

    @Autowired
    public ResourceSoapAdapter(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public ResourceSOAP findResource(String id) {
        ModelMapper modelMapper = new ModelMapper();
        if(resourceService.getResource(id) instanceof Table){
            return modelMapper.map(resourceService.getResource(id), TableSOAP.class);
        }
        if(resourceService.getResource(id) instanceof BallRoom){
            return modelMapper.map(resourceService.getResource(id), BallRoomSOAP.class);
        }
        return modelMapper.map(resourceService.getResource(id), ResourceSOAP.class);
    }

    public String addBallRoom(BallRoomSOAP ballRoomSOAP){
        ModelMapper modelMapper = new ModelMapper();
        if(resourceService.addResource(modelMapper.map(ballRoomSOAP, BallRoom.class))){
            return ballRoomSOAP.getId();
        }
        return "IM SO SORRY";
    }

    public String addTable(TableSOAP tableSOAP) {
        ModelMapper modelMapper = new ModelMapper();
        if (resourceService.addResource(modelMapper.map(tableSOAP, Table.class))) {
            return tableSOAP.getId();
        }
        return "IM SO SORRY";
    }

    public List<TableSOAP> getTables(){
        ModelMapper modelMapper = new ModelMapper();
        return resourceService.getAllTables().stream()
                .map(entity -> modelMapper.map(entity, TableSOAP.class))
                .collect(Collectors.toList());
    }

    public String deleteResource(String id) {
        resourceService.deleteResource(id);
        return "OK";
    }
}
