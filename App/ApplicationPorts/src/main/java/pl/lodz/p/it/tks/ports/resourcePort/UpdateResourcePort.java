package pl.lodz.p.it.tks.ports.resourcePort;

import pl.lodz.p.it.tks.model.Resource;

public interface UpdateResourcePort {
    void updateResource(int id, Resource resource);
}
