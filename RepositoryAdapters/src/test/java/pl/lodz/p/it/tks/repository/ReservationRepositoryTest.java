package pl.lodz.p.it.tks.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.tks.data.*;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class ReservationRepositoryTest {

    ReservationRepository reservationRepository = new ReservationRepository();
    ReservationEnt reservation;

    @BeforeEach
    void setUp() {
        ClientEnt client = new ClientEnt("romek", "password", "Roman", "Bialek", new NormalClientEnt());
        ResourceEnt table = new TableEnt("test", 10, 10, 10);
        reservation = new ReservationEnt("1", table, client, LocalDateTime.of(2002, Month.MARCH, 1, 1, 1));
        reservationRepository.add("1", reservation);
    }

    @Test
    void getReservation() {
        Assertions.assertEquals(reservation, reservationRepository.get("1"));
    }
}