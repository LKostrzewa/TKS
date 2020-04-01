package pl.lodz.p.it.tks.useCases.ReservationUseCase;

import pl.lodz.p.it.tks.model.Reservation;

import java.util.List;

public interface UtilsReservationUseCase {
    double countReservationPrice(String id);
    List<Reservation> getAllReservations();
    List<Reservation> getAllClientReservations(String login);
    Reservation getReservation(String id);
}
