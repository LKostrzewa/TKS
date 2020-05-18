package pl.lodz.p.it.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.tks.exceptions.InactiveClientException;
import pl.lodz.p.it.tks.exceptions.ResourceTakenException;
import pl.lodz.p.it.tks.model.Reservation;
import pl.lodz.p.it.tks.ports.reservationPort.AddReservationPort;
import pl.lodz.p.it.tks.ports.reservationPort.DeleteReservationPort;
import pl.lodz.p.it.tks.ports.reservationPort.GetReservationsPort;
import pl.lodz.p.it.tks.ports.reservationPort.UpdateReservationPort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private AddReservationPort addReservationPort;
    private GetReservationsPort getReservationsPort;
    private DeleteReservationPort deleteReservationPort;
    private UpdateReservationPort updateReservationPort;

    @Autowired
    public ReservationService(AddReservationPort addReservationPort, GetReservationsPort getReservationsPort, DeleteReservationPort deleteReservationPort, UpdateReservationPort updateReservationPort) {
        this.addReservationPort = addReservationPort;
        this.getReservationsPort = getReservationsPort;
        this.deleteReservationPort = deleteReservationPort;
        this.updateReservationPort = updateReservationPort;
    }

    public void startReservation(Reservation reservation) /*Runtime bo w testach wygoniej :)*/throws RuntimeException {
        if(getReservationsPort.getReservedReservations(reservation.getResource().getId()).isPresent())
            throw new ResourceTakenException("Reservation impossible, that resource is already taken");
        if(!reservation.getClient().isActive()){
            throw new InactiveClientException("Cannot reserve, client is inactive");
        }
        else addReservationPort.addReservation(reservation);
    }

    public void endReservation(int id, LocalDateTime end){
        Reservation r = getReservation(id);
        if(r.getClient().isActive()){
            r.setEnding(end);
            updateReservationPort.updateReservation(id, r);
        }
    }

    public void deleteReservation(int id){
        if(getReservationsPort.getReservation(id).getEnding() == null)
            deleteReservationPort.deleteReservation(id);
    }

    public double countReservationPrice(int id){
        Reservation r = getReservationsPort.getReservation(id);
        Duration duration = Duration.between(r.getBeginning(), r.getEnding());
        long diff = duration.toHours();
        double price = r.getResource().getPrice();
        return price*diff - r.getClient().getDiscount(price);
    }

    public List<Reservation> getAllReservations(){
        return getReservationsPort.getAllReservations();
    }

    public List<Reservation> getAllClientReservations(String login){
        return getReservationsPort.getReservationsForClient(login);
    }

    public Reservation getReservation(int id){
        return getReservationsPort.getReservation(id);
    }
}
