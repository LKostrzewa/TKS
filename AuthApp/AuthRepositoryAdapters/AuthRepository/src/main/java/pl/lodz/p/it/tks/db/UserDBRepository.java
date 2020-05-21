package pl.lodz.p.it.tks.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.it.tks.data.UserEnt;

public interface UserDBRepository extends JpaRepository<UserEnt, Integer> {
    UserEnt getByName(String name);
}
