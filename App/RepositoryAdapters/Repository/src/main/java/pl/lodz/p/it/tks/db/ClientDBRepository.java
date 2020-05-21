package pl.lodz.p.it.tks.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.it.tks.data.ClientEnt;

import java.util.List;

public interface ClientDBRepository extends JpaRepository<ClientEnt, Integer> {
    List<ClientEnt> getClientEntByActiveIsTrue();
    void deleteById(Integer id);
    ClientEnt getClientEntByName(String name);
}
