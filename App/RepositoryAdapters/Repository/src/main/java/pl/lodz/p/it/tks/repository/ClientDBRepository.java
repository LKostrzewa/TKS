package pl.lodz.p.it.tks.repository;

import pl.lodz.p.it.tks.data.ClientEnt;

import java.util.List;

public interface ClientDBRepository extends RepositoryDBTemplate<ClientEnt> {
    List<ClientEnt> getClientEntByActive();
}
