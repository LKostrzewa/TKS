package pl.lodz.p.it.tks.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.it.tks.data.UserEnt;

import java.util.UUID;

@Transactional
public interface UserDBRepository extends JpaRepository<UserEnt, Integer> {
    UserEnt getByName(String name);
    boolean existsByKey(UUID key);
    boolean existsByLogin(String name);
    void deleteByKey(UUID key);
    UserEnt getByKey(UUID key);
}
