package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.ReservationViewConverter;
import pl.lodz.p.it.tks.dto.ReservationDTO;
import pl.lodz.p.it.tks.service.ReservationService;
import pl.lodz.p.it.tks.useCases.reservationUseCase.DeleteReservationUseCase;
import pl.lodz.p.it.tks.useCases.reservationUseCase.EndReservationUseCase;
import pl.lodz.p.it.tks.useCases.reservationUseCase.StartReservationUseCase;
import pl.lodz.p.it.tks.useCases.reservationUseCase.UtilsReservationUseCase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationViewAdapter implements StartReservationUseCase, EndReservationUseCase, DeleteReservationUseCase, UtilsReservationUseCase {
    private ReservationService reservationService;
    private ReservationViewConverter reservationViewConverter;

    @Autowired
    public ReservationViewAdapter(ReservationService reservationService) {
        this.reservationService = reservationService;
        reservationViewConverter = new ReservationViewConverter();
    }

    @Override
    public void deleteReservation(String id) {
        reservationService.deleteReservation(id);
    }

    @Override
    public void endReservation(String id, LocalDateTime end) {
        reservationService.endReservation(id, end);
    }

    @Override
    public void startReservation(ReservationDTO reservation) {
        reservationService.startReservation(reservationViewConverter.convertReservationDTO(reservation));
    }

    @Override
    public double countReservationPrice(String id) {
        return reservationService.countReservationPrice(id);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        reservationService.getAllReservations().forEach(r -> reservationDTOS.add(getReservation(r.getId())));
        return reservationDTOS;
    }

    @Override
    public List<ReservationDTO> getAllClientReservations(String login) {
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        reservationService.getAllClientReservations(login).forEach(r -> reservationDTOS.add(getReservation(r.getId())));
        return reservationDTOS;
    }

    @Override
    public ReservationDTO getReservation(String id) {
        return reservationViewConverter.convertReservation(reservationService.getReservation(id));
    }
}
