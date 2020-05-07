package pl.lodz.p.it.tks.useCases.clientUseCase;

import pl.lodz.p.it.tks.dto.ClientDTO;

public interface UpdateClientUseCase {
    void updateClient(String id, ClientDTO client);
}
