package pl.lodz.p.it.tks.converters;


import pl.lodz.p.it.tks.data.ClientEnt;
import pl.lodz.p.it.tks.data.ReservationEnt;
import pl.lodz.p.it.tks.data.ResourceEnt;
import pl.lodz.p.it.tks.model.Reservation;

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
}
