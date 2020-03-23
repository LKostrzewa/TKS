package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.ReservationConverter;
import pl.lodz.p.it.tks.data.ReservationEnt;
import pl.lodz.p.it.tks.model.Reservation;
import pl.lodz.p.it.tks.ports.AddReservationPort;
import pl.lodz.p.it.tks.repository.ReservationRepository;

@Component
public class ReservationRepositoryAdapter implements AddReservationPort {
    private ReservationRepository repository;
    private ReservationConverter converter;

    @Autowired
    public ReservationRepositoryAdapter(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addReservation(Reservation reservation){
        repository.add(reservation.getId(), converter.convertReservation(reservation));
    }


}
