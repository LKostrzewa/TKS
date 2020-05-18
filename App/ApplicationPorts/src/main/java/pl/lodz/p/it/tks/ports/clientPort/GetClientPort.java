package pl.lodz.p.it.tks.ports.clientPort;

import pl.lodz.p.it.tks.model.Client;

import java.util.List;

public interface GetClientPort {
    List<Client> getAllClients();
    List<Client> getAllActiveClients();
    Client getClient(int id);
    Client getClientByName(String name);
}
