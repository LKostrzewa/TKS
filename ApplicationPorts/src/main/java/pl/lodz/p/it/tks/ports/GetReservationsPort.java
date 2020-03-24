package pl.lodz.p.it.tks.ports;

import pl.lodz.p.it.tks.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface GetReservationsPort {
    Reservation getReservation(String id);
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsForClient(String login);
    List<Reservation> getReservationsForResource(String id);
    Optional<Reservation> getReservedReservations(String id);
}
