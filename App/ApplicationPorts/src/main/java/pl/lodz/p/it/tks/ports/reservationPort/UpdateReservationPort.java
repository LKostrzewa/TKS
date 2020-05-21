package pl.lodz.p.it.tks.ports.reservationPort;

import pl.lodz.p.it.tks.model.Reservation;

public interface UpdateReservationPort {
    void updateReservation(int id, Reservation reservation);
}
