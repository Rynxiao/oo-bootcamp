package com.thoughtworks.bootcamp.parkingLot;

import com.thoughtworks.bootcamp.exceptions.InvalidTicketException;
import com.thoughtworks.bootcamp.exceptions.ParkingForbidException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingBoyTest {

  private ParkingLot parkingLot1;
  private ParkingLot parkingLot2;
  private List<ParkingLot> parkingLotList;
  private ParkingBoy parkingBoy;

  @BeforeEach
  void setUp() {
    parkingLot1 = new ParkingLot(1);
    parkingLot2 = new ParkingLot(1);
    parkingLotList = newArrayList(parkingLot1, parkingLot2);
    parkingBoy = new ParkingBoy(parkingLotList);
  }

  @Test
  void should_return_ticket_when_park_to_parking_boy_given_a_car_and_parking_lots_are_not_full() {
    Car car = new Car(1);

    Ticket ticket = parkingBoy.park(car);

    assertNotNull(ticket);
    assertTrue(parkingLot1.isCarExist(ticket) || parkingLot2.isCarExist(ticket));
  }

  @Test
  void should_throw_exception_when_park_to_parking_boy_given_a_car_and_parking_lots_are_full() {
    Car car1 = new Car(1);
    Car car2 = new Car(2);

    parkingBoy.park(car1);
    parkingBoy.park(car2);

    Car parkingCar = new Car(3);
    assertThrows(ParkingForbidException.class, () -> parkingBoy.park(parkingCar));
  }

  @Test
  void should_return_car_when_fetch_given_valid_ticket() {
    Car parkingCar = new Car(1);
    Ticket ticket = parkingBoy.park(parkingCar);

    Car fetchedCar = parkingBoy.fetch(ticket);

    assertEquals(parkingCar, fetchedCar);
  }

  @Test
  void should_throw_exception_when_fetch_given_invalid_ticket() {
    Car parkingCar = new Car(1);
    parkingBoy.park(parkingCar);

    Ticket ticket = new Ticket(2);
    assertThrows(InvalidTicketException.class, () -> parkingBoy.fetch(ticket));
  }

  @Test
  void should_return_ticket_when_park_given_that_full_parking_lots_fetch_a_car() {
    Car car1 = new Car(1);
    Car car2 = new Car(2);
    Ticket ticket1 = parkingBoy.park(car1);
    parkingBoy.park(car2);
    parkingBoy.fetch(ticket1);
    Car car = new Car(3);

    Ticket ticket = parkingBoy.park(car);

    assertNotNull(ticket);
  }

  @Test
  void should_throw_exception_when_fetch_given_no_ticket() {
    Car parkingCar = new Car(1);
    parkingBoy.park(parkingCar);

    assertThrows(InvalidTicketException.class, () -> parkingBoy.fetch(null));
  }
}