package pl.lodz.p.it.tks.useCases.clientUseCase;

import pl.lodz.p.it.tks.dto.ClientDTO;

public interface AddClientUseCase {
    boolean addClient(ClientDTO client);
}
