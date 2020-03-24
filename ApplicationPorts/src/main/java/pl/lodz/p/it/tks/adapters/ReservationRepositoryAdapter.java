package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.ReservationConverter;
import pl.lodz.p.it.tks.data.ReservationEnt;
import pl.lodz.p.it.tks.model.Reservation;
import pl.lodz.p.it.tks.ports.AddReservationPort;
import pl.lodz.p.it.tks.ports.DeleteReservationPort;
import pl.lodz.p.it.tks.ports.GetReservationsPort;
import pl.lodz.p.it.tks.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class ReservationRepositoryAdapter implements AddReservationPort, DeleteReservationPort, GetReservationsPort {
    private ReservationRepository repository;
    private ReservationConverter converter;

    @Autowired
    public ReservationRepositoryAdapter(ReservationRepository repository) {
        this.repository = repository;
        converter = new ReservationConverter();
    }

    @Override
    public void addReservation(Reservation reservation){
        repository.add(reservation.getId(), converter.convertReservation(reservation));
    }


    @Override
    public void deleteReservation(String id) {
        repository.delete(id);
    }

    @Override
    public Reservation getReservation(String id) {
        return converter.convertReservationEnt(repository.get(id));
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        for(ReservationEnt reservationEnt : repository.getAll()){
            reservations.add(converter.convertReservationEnt(reservationEnt));
        }
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsForClient(String login) {
        List<Reservation> reservations = new ArrayList<>();
        for(ReservationEnt reservationEnt : repository.getReservationsForClient(login)){
            reservations.add(converter.convertReservationEnt(reservationEnt));
        }
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsForResource(String id) {
        List<Reservation> reservations = new ArrayList<>();
        for(ReservationEnt reservationEnt : repository.getReservationsForResource(id)){
            reservations.add(converter.convertReservationEnt(reservationEnt));
        }
        return reservations;
    }

    @Override
    public Optional<Reservation> getReservedReservations(String id) {
        List<Reservation> reservations = new ArrayList<>();
        List<ReservationEnt> reservationsEnts = repository.getReservedReservations(id).stream().collect(Collectors.toList());
        for(ReservationEnt reservationEnt : reservationsEnts){
            reservations.add(converter.convertReservationEnt(reservationEnt));
        }
        return reservations.stream().findAny();
    }
}
