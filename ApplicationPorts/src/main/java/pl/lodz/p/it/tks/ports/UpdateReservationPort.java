package pl.lodz.p.it.tks.ports;

import pl.lodz.p.it.tks.model.Reservation;

public interface UpdateReservationPort {
    void updateReservation(String id, Reservation reservation);
}
