package pl.lodz.p.it.tks.adapters;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lodz.p.it.tks.db.ReservationDBRepository;
import pl.lodz.p.it.tks.model.*;
import pl.lodz.p.it.tks.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.time.Month;


class ReservationRepositoryAdapterTest {

    @Autowired
    ReservationRepositoryAdapter reservationRepositoryAdapter;
    Reservation reservation;

    /*@BeforeEach
    void init() {
        Client client = new Client("romek", "Roman", "Bialek", new NormalClient());
        Resource table = new Table("test", 10, 10, 10);
        reservation = new Reservation("1", table, client, LocalDateTime.of(2002, Month.MARCH, 1, 1, 1));
        reservationRepositoryAdapter.addReservation(reservation);
    }

    @Test
    void getReservation() {
        Assertions.assertEquals(reservation.getId(), reservationRepositoryAdapter.getReservation("1").getId());
        Reservation res1 = reservationRepositoryAdapter.getReservation("1");
        res1.setEnding(LocalDateTime.now());
        Assertions.assertNotEquals(res1.getEnding(), reservation.getEnding());
    }*/

    @Test
    void getAllReservationTest() {

    }
}