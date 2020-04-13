package pl.lodz.p.it.tks.useCases.resourceUseCase;

import pl.lodz.p.it.tks.dto.ResourceDTO;

public interface UpdateResourceUseCase {
    void updateResource(String id, ResourceDTO resource);
}
