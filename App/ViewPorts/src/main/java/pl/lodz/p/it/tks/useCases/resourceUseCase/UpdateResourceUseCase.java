package pl.lodz.p.it.tks.useCases.resourceUseCase;

import pl.lodz.p.it.tks.dto.ResourceDTO;

public interface UpdateResourceUseCase {
    void updateResource(int id, ResourceDTO resource);
}
