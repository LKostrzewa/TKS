package pl.lodz.p.it.tks.useCases.clientUseCase;

import java.util.UUID;

public interface DeleteClientUseCase {
    void deleteClient(int id);
    void deleteClientByKey(UUID key);
}
