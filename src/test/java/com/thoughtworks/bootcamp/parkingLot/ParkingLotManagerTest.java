package com.thoughtworks.bootcamp.parkingLot;

import com.thoughtworks.bootcamp.exceptions.InvalidTicketException;
import com.thoughtworks.bootcamp.exceptions.ParkingForbidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ParkingLotManagerTest {
  private ParkingLotManager parkingLotManager;
  private List<ParkingInterface> parkingLotList;

  @BeforeEach
  void setUp() {
    ParkingLot parkingLot1 = new ParkingLot(1);
    ParkingLot parkingLot2 = new ParkingLot(1);
    ParkingLot parkingLot3 = new ParkingLot(1);
    ParkingLot parkingLot4 = new ParkingLot(1);
    ParkingLot parkingLot5 = new ParkingLot(1);
    ParkingLot parkingLot6 = new ParkingLot(1);
    GeneralParkingBoy generalParkingBoy =
        new GeneralParkingBoy(newArrayList(parkingLot1, parkingLot2));
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(newArrayList(parkingLot3, parkingLot4));
    parkingLotList = newArrayList(generalParkingBoy, smartParkingBoy, parkingLot5, parkingLot6);
    parkingLotManager = new ParkingLotManager(parkingLotList);
  }

  @Test
  void
      should_return_a_ticket_when_park_given_parking_lots_are_not_full_and_a_parking_manager_and_has_no_parking_boy() {
    Car car = new Car(1);

    Ticket ticket = parkingLotManager.park(car);

    assertNotNull(ticket);
  }

  @Test
  void should_return_a_ticket_when_park_given_parking_lots_are_not_full_and_a_parking_manager() {
    Car car = new Car(1);

    Ticket ticket = parkingLotManager.park(car);

    assertNotNull(ticket);
  }

  @Test
  void should_throw_exception_when_park_given_parking_lots_are_full_and_a_parking_manager() {
    for (int i = 0; i < 6; i++) {
      Car car = new Car(i);
      parkingLotManager.park(car);
    }

    Car comingCar = new Car(4);

    assertThrows(ParkingForbidException.class, () -> parkingLotManager.park(comingCar));
  }

  @Test
  void should_return_a_ticket_when_fetch_car_given_a_valid_ticket_to_parking_manager() {
    Car existCar = new Car(1);
    Ticket existTicket = parkingLotManager.park(existCar);

    Car fetchedCar = parkingLotManager.fetch(existTicket);

    assertEquals(existCar, fetchedCar);
  }

  @Test
  void should_throw_exception_when_fetch_car_given_a_invalid_ticket_to_parking_manager() {
    Car existCar = new Car(1);
    parkingLotManager.park(existCar);

    Ticket invalidTicket = new Ticket(2);

    assertThrows(InvalidTicketException.class, () -> parkingLotManager.fetch(invalidTicket));
  }

  @Test
  void should_return_a_ticket_when_parking_car_given_full_parking_lots_fetched_a_car() {
    for (int i = 0; i < 5; i++) {
      Car car = new Car(i);
      parkingLotManager.park(car);
    }
    Ticket ticket3 = parkingLotManager.park(new Car(6));
    parkingLotManager.fetch(ticket3);

    Car comingCar = new Car(7);

    Ticket comingTicket = parkingLotManager.park(comingCar);

    assertNotNull(comingTicket);
  }

  @Test
  void should_throw_exception_when_fetch_given_no_ticket() {
    Car parkingCar = new Car(1);
    parkingLotManager.park(parkingCar);

    assertThrows(InvalidTicketException.class, () -> parkingLotManager.fetch(null));
  }
}
