package pl.lodz.p.it.tks.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.it.tks.data.BallRoomEnt;

public interface BallRoomDBRepository extends JpaRepository<BallRoomEnt, Integer> {
}
