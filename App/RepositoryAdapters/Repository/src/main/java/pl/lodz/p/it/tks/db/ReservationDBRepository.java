package pl.lodz.p.it.tks.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.it.tks.data.ReservationEnt;

import java.util.List;

public interface ReservationDBRepository extends JpaRepository<ReservationEnt, Integer> {
    List<ReservationEnt> getReservationEntByClient_Id(Integer Id);
    List<ReservationEnt> getReservationEntByClient_Name(String name);
    List<ReservationEnt> getReservationEntByResource_Id(Integer id);
    List<ReservationEnt> getReservationEntByEndingIsNullAndResource_Id(Integer id);
}
