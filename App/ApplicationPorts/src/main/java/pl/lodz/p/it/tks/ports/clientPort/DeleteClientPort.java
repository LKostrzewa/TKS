package pl.lodz.p.it.tks.ports.clientPort;

import java.util.UUID;

public interface DeleteClientPort {
    void deleteClient(int id);
    void deleteByKey(UUID uuid);
}
