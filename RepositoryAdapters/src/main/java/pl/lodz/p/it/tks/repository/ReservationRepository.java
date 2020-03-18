package pl.lodz.p.it.tks.repository;

import org.springframework.stereotype.Repository;
import pl.lodz.p.it.tks.data.ReservationEnt;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ReservationRepository extends RepositoryTemplate<ReservationEnt> {

    public List<ReservationEnt> getReservationsForClient(String login){
        return getAll().stream().filter(
                r -> r.getClient().getLogin().equals(login))
                .collect(Collectors.toList());
    }

    public List<ReservationEnt> getReservationsForResource(String id){
        return getAll().stream().filter(
                r -> r.getResource().getId().equals(id))
                .collect(Collectors.toList());
    }

    public Optional<ReservationEnt> getReservedReservations(String id){
        return getReservationsForResource(id).stream().filter(
                r -> r.getEnding() == null).findAny();
    }
}
