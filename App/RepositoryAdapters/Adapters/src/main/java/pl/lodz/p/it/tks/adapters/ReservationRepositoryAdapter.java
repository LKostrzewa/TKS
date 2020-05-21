package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.ReservationConverter;
import pl.lodz.p.it.tks.data.ReservationEnt;
import pl.lodz.p.it.tks.db.ReservationDBRepository;
import pl.lodz.p.it.tks.model.Reservation;
import pl.lodz.p.it.tks.ports.reservationPort.AddReservationPort;
import pl.lodz.p.it.tks.ports.reservationPort.DeleteReservationPort;
import pl.lodz.p.it.tks.ports.reservationPort.GetReservationsPort;
import pl.lodz.p.it.tks.ports.reservationPort.UpdateReservationPort;
import pl.lodz.p.it.tks.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ReservationRepositoryAdapter implements AddReservationPort, DeleteReservationPort, GetReservationsPort, UpdateReservationPort {
    private ReservationDBRepository repository;
    private ReservationConverter converter;

    @Autowired
    public ReservationRepositoryAdapter(ReservationDBRepository repository) {
        this.repository = repository;
        converter = new ReservationConverter();
    }

    @Override
    public void addReservation(Reservation reservation){
        repository.save(converter.convertReservation(reservation));
    }


    @Override
    public void deleteReservation(int id) {
        repository.deleteById(id);
    }

    @Override
    public Reservation getReservation(int id) {
        return converter.convertReservationEnt(repository.getOne(id));
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        for(ReservationEnt reservationEnt : repository.findAll()){
            reservations.add(converter.convertReservationEnt(reservationEnt));
        }
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsForClient(String login) {
        List<Reservation> reservations = new ArrayList<>();
        //nw czy nie powinno być getReservationEntByClient_Name czy costakiego - login to chyba nie id
        for(ReservationEnt reservationEnt : repository.getReservationEntByClient_Name(login)){
            reservations.add(converter.convertReservationEnt(reservationEnt));
        }
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsForResource(int id) {
        List<Reservation> reservations = new ArrayList<>();
        for(ReservationEnt reservationEnt : repository.getReservationEntByResource_Id(id)){
            reservations.add(converter.convertReservationEnt(reservationEnt));
        }
        return reservations;
    }

    @Override
    public Optional<Reservation> getReservedReservations(int id) {
        List<Reservation> reservations = new ArrayList<>();
        List<ReservationEnt> reservationsEnts = repository.getReservationEntByEndingIsNullAndResource_Id(id);
        //Java 9+ version
        //List<ReservationEnt> reservationsEnts = repository.getReservedReservations(id).stream().collect(Collectors.toList());
        for(ReservationEnt reservationEnt : reservationsEnts){
            reservations.add(converter.convertReservationEnt(reservationEnt));
        }
        return reservations.stream().findAny();
    }

    @Override
    public void updateReservation(int id, Reservation reservation) {
        if(repository.existsById(id)){
            repository.save(converter.convertReservation(reservation));
        }
    }
}
