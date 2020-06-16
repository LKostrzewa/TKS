package pl.lodz.p.it.tks.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.it.tks.data.UserEnt;

@Transactional
public interface UserDBRepository extends JpaRepository<UserEnt, Integer> {
    UserEnt getByName(String name);
}
