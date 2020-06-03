package pl.lodz.p.it.tks.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.tks.data.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationRepositoryTest {

    ReservationRepository reservationRepository = new ReservationRepository();
    ReservationEnt reservation;

    @BeforeEach
    void setUp() {
        ClientEnt client = new ClientEnt(1, "Roman", "Bialek", "Normal", UUID.randomUUID());
        ResourceEnt table = new TableEnt(2, 10, 10, 10);
        reservation = new ReservationEnt(2, table, client, LocalDateTime.of(2002, Month.MARCH, 1, 1, 1));
        reservationRepository.add("1", reservation);
    }

    @Test
    void getReservation() {
        assertEquals(reservation, reservationRepository.get("1"));
    }
}