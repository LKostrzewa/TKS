package pl.lodz.p.it.tks.ports;

import pl.lodz.p.it.tks.model.Resource;

public interface AddResourcePort {
    //pytanie brzmi Resource czy ResourceEnt ???
    boolean addResource(Resource resource);
}
