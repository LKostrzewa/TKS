package pl.lodz.p.it.tks.repository;

import pl.lodz.p.it.tks.data.ReservationEnt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface ReservationDBRepository extends RepositoryDBTemplate<ReservationEnt> {
    //Tu nw jak je nazwać legitnie zeby robily co trza
    List<ReservationEnt> getReservationsForClient(String login);
    List<ReservationEnt> getReservationsForResource(String id);
    List<ReservationEnt> getReservedReservations(String id);
}
