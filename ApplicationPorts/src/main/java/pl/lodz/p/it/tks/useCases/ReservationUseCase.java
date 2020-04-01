package pl.lodz.p.it.tks.useCases;

import pl.lodz.p.it.tks.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationUseCase {
    void startReservation(Reservation reservation);
    void endReservation(String id, LocalDateTime end);
    void deleteReservation(String id);
    double countReservationPrice(String id);
    List<Reservation> getAllReservations();
    List<Reservation> getAllClientReservations(String login);
    Reservation getReservation(String id);
}
