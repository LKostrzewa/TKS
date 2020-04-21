package pl.lodz.p.it.tks.converters;

import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.dto.ReservationDTO;
import pl.lodz.p.it.tks.dto.ResourceDTO;
import pl.lodz.p.it.tks.model.*;

public class ReservationViewConverter {

    private UserViewConverter userViewConverter;
    private ResourceViewConverter resourceViewConverter;

    public ReservationViewConverter() {
        userViewConverter = new UserViewConverter();
        resourceViewConverter = new ResourceViewConverter();
    }

    public Reservation convertReservationDTO(ReservationDTO reservationDTO){
        Client client = (Client) userViewConverter.convertUserDTO(reservationDTO.getClient());
        Resource resource = resourceViewConverter.convertResourceDTO(reservationDTO.getResource());
        Reservation reservation = new Reservation(reservationDTO.getId(), resource, client, reservationDTO.getBeginning());
        reservation.setEnding(reservationDTO.getEnding());
        return reservation;
    }

    public ReservationDTO convertReservation(Reservation reservation){
        ClientDTO client = userViewConverter.convertClient(reservation.getClient());
        ResourceDTO resourceDTO;
        if(reservation.getResource() instanceof Table){
            resourceDTO = resourceViewConverter.convertTable((Table)reservation.getResource());
        }
        else {
            resourceDTO = resourceViewConverter.convertBallRoom((BallRoom)reservation.getResource());
        }
        ReservationDTO reservationDTO = new ReservationDTO(reservation.getId(), resourceDTO, client, reservation.getBeginning());
        reservationDTO.setEnding(reservation.getEnding());
        return reservationDTO;
    }
}
