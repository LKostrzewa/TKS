package pl.lodz.p.it.tks.converters;


import pl.lodz.p.it.tks.data.*;
import pl.lodz.p.it.tks.model.Client;
import pl.lodz.p.it.tks.model.Reservation;
import pl.lodz.p.it.tks.model.Resource;
import pl.lodz.p.it.tks.model.Table;

public class ReservationConverter {
    private UserConverter userConverter;
    private ResourceConverter resourceConverter;

    public ReservationConverter() {
        userConverter = new UserConverter();
        resourceConverter = new ResourceConverter();
    }

    public ReservationEnt convertReservation(Reservation reservation){
        ClientEnt clientEnt = (ClientEnt) userConverter.convertUser(reservation.getClient());
        ResourceEnt resourceEnt = resourceConverter.convertResource(reservation.getResource());
        ReservationEnt reservationEnt = new ReservationEnt(reservation.getId(), resourceEnt, clientEnt, reservation.getBeginning());
        reservationEnt.setEnding(reservation.getEnding());
        return reservationEnt;
    }

    public Reservation convertReservationEnt(ReservationEnt reservationEnt){
        Client client = userConverter.convertClientEnt(reservationEnt.getClient());
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
