package pl.lodz.p.it.tks.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.it.tks.data.ClientEnt;

import java.util.List;

public interface ClientDBRepository extends JpaRepository<ClientEnt, String> {
    List<ClientEnt> getClientEntByActiveIsTrue();
    void deleteById(String id);
    void addClient(ClientEnt clientEnt);
}
