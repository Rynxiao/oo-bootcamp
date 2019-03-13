package com.thoughtworks.bootcamp.parkingLot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ParkingLotManagerTest {
  private ParkingLotManager parkingLotManager;

  @BeforeEach
  void setUp() {
    ParkingLot parkingLot1 = new ParkingLot(1);
    ParkingLot parkingLot2 = new ParkingLot(2);
    ParkingLot parkingLot3 = new ParkingLot(3);
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
}
