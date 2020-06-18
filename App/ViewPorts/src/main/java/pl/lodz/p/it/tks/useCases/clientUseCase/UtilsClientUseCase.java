package pl.lodz.p.it.tks.useCases.clientUseCase;

import pl.lodz.p.it.tks.dto.ClientDTO;

import java.util.List;
import java.util.UUID;

public interface UtilsClientUseCase {
    ClientDTO getClient(int id);
    ClientDTO getClientByName(String name);
    List<ClientDTO> getAllClients();
    List<ClientDTO> getAllActiveClients();
    ClientDTO getClientByKey(UUID key);
}
