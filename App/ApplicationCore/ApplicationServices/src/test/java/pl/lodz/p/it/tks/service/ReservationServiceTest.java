package pl.lodz.p.it.tks.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.tks.adapters.ReservationRepositoryAdapter;
import pl.lodz.p.it.tks.model.*;
import pl.lodz.p.it.tks.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    ReservationService reservationService;
    Reservation reservation;
    ReservationRepositoryAdapter reservationRepositoryAdapter = new ReservationRepositoryAdapter(new ReservationRepository());

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService(reservationRepositoryAdapter, reservationRepositoryAdapter, reservationRepositoryAdapter, reservationRepositoryAdapter);
        Client client = new Client("romek", "Roman", "Bialek", new NormalClient());
        Resource table = new Table("test", 10, 10, 10);
        reservation = new Reservation("1", table, client, LocalDateTime.of(2002, Month.MARCH, 1, 1, 1));
    }

    @Test
    void endReservation() {
        reservationService.startReservation(reservation);
        Assertions.assertEquals(reservationService.getReservation("1").getId(), "1");
        reservationService.getReservation("1");
        reservationService.endReservation("1", LocalDateTime.of(2020, Month.MARCH, 1, 1, 1));
        Assertions.assertEquals(reservationService.getReservation("1").getEnding(), LocalDateTime.of(2020, Month.MARCH, 1, 1, 1));
    }
}