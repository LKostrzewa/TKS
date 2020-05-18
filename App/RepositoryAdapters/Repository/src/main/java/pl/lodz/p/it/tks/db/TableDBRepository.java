package pl.lodz.p.it.tks.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.it.tks.data.TableEnt;

public interface TableDBRepository extends JpaRepository<TableEnt, Integer> {
}
