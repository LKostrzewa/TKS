package pl.lodz.p.it.tks.converters;


import pl.lodz.p.it.tks.data.*;
import pl.lodz.p.it.tks.model.Client;
import pl.lodz.p.it.tks.model.Reservation;
import pl.lodz.p.it.tks.model.Resource;

public class ReservationConverter {
    private ClientConverter clientConverter;
    private ResourceConverter resourceConverter;

    public ReservationConverter() {
        clientConverter = new ClientConverter();
        resourceConverter = new ResourceConverter();
    }

    public ReservationEnt convertReservation(Reservation reservation){
        ClientEnt clientEnt = (ClientEnt) clientConverter.convertClient(reservation.getClient());
        ResourceEnt resourceEnt = resourceConverter.convertResource(reservation.getResource());
        ReservationEnt reservationEnt = new ReservationEnt(reservation.getId(), resourceEnt, clientEnt, reservation.getBeginning());
        reservationEnt.setEnding(reservation.getEnding());
        return reservationEnt;
    }

    public Reservation convertReservationEnt(ReservationEnt reservationEnt){
        Client client = clientConverter.convertClientEnt(reservationEnt.getClient());
        Resource resource;
        if(reservationEnt.getResource() instanceof TableEnt){
            resource = resourceConverter.convertTableEnt((TableEnt)reservationEnt.getResource());
        }
        else {
            resource = resourceConverter.convertBallRoomEnt((BallRoomEnt)reservationEnt.getResource());
        }
        Reservation reservation = new Reservation(reservationEnt.getId(), resource, client, reservationEnt.getBeginning());
        reservation.setEnding(reservationEnt.getEnding());
        return reservation;
    }
}
