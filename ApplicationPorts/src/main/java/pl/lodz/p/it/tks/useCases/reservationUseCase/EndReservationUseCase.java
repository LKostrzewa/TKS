package pl.lodz.p.it.tks.useCases.reservationUseCase;

import java.time.LocalDateTime;

public interface EndReservationUseCase {
    void endReservation(String id, LocalDateTime end);
}
