package pl.lodz.p.it.tks.useCases.ResourceUseCase;

import pl.lodz.p.it.tks.model.Resource;

public interface UpdateResourceUseCase {
    void updateResource(String id, Resource resource);
}
