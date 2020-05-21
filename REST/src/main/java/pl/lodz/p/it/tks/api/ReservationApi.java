package pl.lodz.p.it.tks.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.tks.dto.ReservationDTO;
import pl.lodz.p.it.tks.useCases.reservationUseCase.StartReservationUseCase;
import pl.lodz.p.it.tks.useCases.reservationUseCase.UtilsReservationUseCase;

import java.util.List;


@RestController
@RequestMapping("/api/reservations")
public class ReservationApi {

    private UtilsReservationUseCase utilsReservationUseCase;
    private StartReservationUseCase startReservationUseCase;

    @Autowired
    public ReservationApi(UtilsReservationUseCase utilsReservationUseCase, StartReservationUseCase startReservationUseCase) {
        this.utilsReservationUseCase = utilsReservationUseCase;
        this.startReservationUseCase = startReservationUseCase;
    }

    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        return utilsReservationUseCase.getAllReservations();
    }

    @PostMapping
    public void addReservations(@RequestBody ReservationDTO reservationDTO) {
        startReservationUseCase.startReservation(reservationDTO);
    }
}
