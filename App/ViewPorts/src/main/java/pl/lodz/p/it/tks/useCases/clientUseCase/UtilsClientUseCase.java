package pl.lodz.p.it.tks.useCases.clientUseCase;

import pl.lodz.p.it.tks.dto.ClientDTO;

import java.util.List;

public interface UtilsClientUseCase {
    ClientDTO getClient(String login);
    List<ClientDTO> getAllClients();
    List<ClientDTO> getAllActiveClients();
}
