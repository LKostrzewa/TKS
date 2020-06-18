package pl.lodz.p.it.tks.ports.clientPort;

import pl.lodz.p.it.tks.model.Client;

import java.util.List;
import java.util.UUID;

public interface GetClientPort {
    List<Client> getAllClients();
    List<Client> getAllActiveClients();
    Client getClient(int id);
    Client getClientByName(String name);
    Client getClientByKey(UUID uuid);
}
