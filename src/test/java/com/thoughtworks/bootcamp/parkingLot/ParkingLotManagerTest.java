package com.thoughtworks.bootcamp.parkingLot;

import com.thoughtworks.bootcamp.exceptions.ParkingForbidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ParkingLotManagerTest {
  private ParkingLotManager parkingLotManager;

  @BeforeEach
  void setUp() {
    ParkingLot parkingLot1 = new ParkingLot(1);
    ParkingLot parkingLot2 = new ParkingLot(1);
    ParkingLot parkingLot3 = new ParkingLot(1);
    GeneralParkingBoy generalParkingBoy =
        new GeneralParkingBoy(newArrayList(parkingLot1, parkingLot2));
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(newArrayList(parkingLot1, parkingLot3));
    parkingLotManager = new ParkingLotManager(newArrayList(generalParkingBoy, smartParkingBoy));
  }

  @Test
  void should_return_a_ticket_when_park_given_parking_lots_are_not_full_and_a_parking_manager() {
    Car car = new Car(1);

    Ticket ticket = parkingLotManager.park(car);

    assertNotNull(ticket);
  }

  @Test
  void should_throw_exception_when_park_given_parking_lots_are_full_and_a_parking_manager() {
    Car car1 = new Car(1);
    Car car2 = new Car(1);
    Car car3 = new Car(1);
    parkingLotManager.park(car1);
    parkingLotManager.park(car2);
    parkingLotManager.park(car3);

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
}
