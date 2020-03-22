package pl.lodz.p.it.tks.ports;

import pl.lodz.p.it.tks.model.Resource;

public interface UpdateResourcePort {
    void updateResource(String id, Resource resource);
}
