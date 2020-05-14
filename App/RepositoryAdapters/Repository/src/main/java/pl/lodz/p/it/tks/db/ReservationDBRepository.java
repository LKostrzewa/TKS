package pl.lodz.p.it.tks.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.it.tks.data.ReservationEnt;

import java.util.List;

public interface ReservationDBRepository extends JpaRepository<ReservationEnt, String> {
    List<ReservationEnt> getReservationEntByClient_Id(String Id);
    List<ReservationEnt> getReservationEntByResource_Id(String id);
    List<ReservationEnt> getReservationEntByEndingIsNullAndResource_Id(String id);
}
