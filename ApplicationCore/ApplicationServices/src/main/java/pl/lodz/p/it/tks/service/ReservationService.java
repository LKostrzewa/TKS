package pl.lodz.p.it.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.tks.exceptions.InactiveClientException;
import pl.lodz.p.it.tks.exceptions.ResourceTakenException;
import pl.lodz.p.it.tks.model.Reservation;
import pl.lodz.p.it.tks.repository.ReservationRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {


    private ReservationRepository reservations;

    @Autowired
    public ReservationService(ReservationRepository reservations) {
        this.reservations = reservations;
    }

    public void startReservation(Reservation reservation) /*Runtime bo w testach wygoniej :)*/throws RuntimeException {
        if(reservations.getReservedReservations(reservation.getResource().getId()).isPresent())
            throw new ResourceTakenException("Reservation impossible, that resource is already taken");
        if(!reservation.getClient().isActive()){
            throw new InactiveClientException("Cannot reserve, client is inactive");
        }
        else reservations.add(reservation.getId(), reservation);
    }

    public void endReservation(String id, LocalDateTime end){
        Reservation r = getReservation(id);
        if(r.getClient().isActive())
            r.setEnding(end);
    }

    public void deleteReservation(String id){
        if(reservations.get(id).getEnding() == null)
            reservations.delete(id);
    }

    public double countReservationPrice(String id){
        Reservation r = reservations.get(id);
        Duration duration = Duration.between(r.getBeginning(), r.getEnding());
        long diff = duration.toHours();
        double price = r.getResource().getPrice();
        return price*diff - r.getClient().getDiscount(price);
    }

    public List<Reservation> getAllReservations(){
        return reservations.getAll();
    }

    public List<Reservation> getAllClientReservations(String login){
        return reservations.getReservationsForClient(login);
    }

    public Reservation getReservation(String id){
        return reservations.get(id);
    }
}
