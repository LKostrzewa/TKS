package pl.lodz.p.it.tks.adapters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.model.BallRoom;
import pl.lodz.p.it.tks.model.Table;
import pl.lodz.p.it.tks.service.ResourceService;
import pl.lodz.p.it.tks.soap.BallRoomSOAP;
import pl.lodz.p.it.tks.soap.ResourceSOAP;
import pl.lodz.p.it.tks.soap.TableSOAP;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResourceSoapAdapter {

    private ResourceService resourceService;
    private ModelMapper modelMapper;

    @Autowired
    public ResourceSoapAdapter(ResourceService resourceService) {
        this.resourceService = resourceService;
        modelMapper = new ModelMapper();
    }


    public ResourceSOAP findResource(String id) {
        if(resourceService.getResource(id) instanceof Table){
            return modelMapper.map(resourceService.getResource(id), TableSOAP.class);
        }
        if(resourceService.getResource(id) instanceof BallRoom){
            return modelMapper.map(resourceService.getResource(id), BallRoomSOAP.class);
        }
        return modelMapper.map(resourceService.getResource(id), ResourceSOAP.class);
    }

    public String addBallRoom(BallRoomSOAP ballRoomSOAP){
        if(resourceService.addResource(modelMapper.map(ballRoomSOAP, BallRoom.class))){
            return ballRoomSOAP.getId();
        }
        return "IM SO SORRY";
    }

    public String addTable(TableSOAP tableSOAP) {
        if (resourceService.addResource(modelMapper.map(tableSOAP, Table.class))) {
            return tableSOAP.getId();
        }
        return "IM SO SORRY";
    }

    public List<TableSOAP> getTables(){
        return resourceService.getAllTables().stream()
                .map(entity -> modelMapper.map(entity, TableSOAP.class))
                .collect(Collectors.toList());
    }

    public String deleteResource(String id) {
        resourceService.deleteResource(id);
        return "OK";
    }
}
