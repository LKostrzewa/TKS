package pl.lodz.p.it.tks.adapters;

import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.dto.ReservationDTO;
import pl.lodz.p.it.tks.useCases.reservationUseCase.DeleteReservationUseCase;
import pl.lodz.p.it.tks.useCases.reservationUseCase.EndReservationUseCase;
import pl.lodz.p.it.tks.useCases.reservationUseCase.StartReservationUseCase;
import pl.lodz.p.it.tks.useCases.reservationUseCase.UtilsReservationUseCase;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReservationViewAdapter implements StartReservationUseCase, EndReservationUseCase, DeleteReservationUseCase, UtilsReservationUseCase {
    @Override
    public void deleteReservation(String id) {

    }

    @Override
    public void endReservation(String id, LocalDateTime end) {

    }

    @Override
    public void startReservation(ReservationDTO reservation) {

    }

    @Override
    public double countReservationPrice(String id) {
        return 0;
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return null;
    }

    @Override
    public List<ReservationDTO> getAllClientReservations(String login) {
        return null;
    }

    @Override
    public ReservationDTO getReservation(String id) {
        return null;
    }
}
