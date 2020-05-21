package pl.lodz.p.it.tks.useCases.reservationUseCase;



import pl.lodz.p.it.tks.dto.ReservationDTO;

import java.util.List;

public interface UtilsReservationUseCase {
    double countReservationPrice(int id);
    List<ReservationDTO> getAllReservations();
    List<ReservationDTO> getAllClientReservations(String login);
    ReservationDTO getReservation(int id);
}
