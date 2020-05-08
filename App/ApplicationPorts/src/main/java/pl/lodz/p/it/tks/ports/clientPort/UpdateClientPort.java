package pl.lodz.p.it.tks.ports.clientPort;

import pl.lodz.p.it.tks.model.Client;

public interface UpdateClientPort {

    void updateClient(String id, Client client);
}
