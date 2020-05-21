package pl.lodz.p.it.tks.ports.reservationPort;

import pl.lodz.p.it.tks.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface GetReservationsPort {
    Reservation getReservation(int id);
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsForClient(String login);
    List<Reservation> getReservationsForResource(int id);
    Optional<Reservation> getReservedReservations(int id);
}
