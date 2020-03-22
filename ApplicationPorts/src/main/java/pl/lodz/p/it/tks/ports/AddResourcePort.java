package pl.lodz.p.it.tks.ports;

import pl.lodz.p.it.tks.data.ResourceEnt;
import pl.lodz.p.it.tks.model.Resource;

public interface AddResourcePort {
    ResourceEnt addResource(Resource resource);
}
