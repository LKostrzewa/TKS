package pl.lodz.p.it.tks.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.it.tks.data.ClientEnt;

import java.util.List;
import java.util.UUID;

@Transactional
public interface ClientDBRepository extends JpaRepository<ClientEnt, Integer> {
    List<ClientEnt> getClientEntByActiveIsTrue();
    void deleteById(Integer id);
    void deleteByKey(UUID key);
    ClientEnt getClientEntByName(String name);
    boolean existsByKey(UUID key);
}
