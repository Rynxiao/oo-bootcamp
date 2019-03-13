package com.thoughtworks.bootcamp.parkingLot;

import com.thoughtworks.bootcamp.exceptions.InvalidTicketException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SmartParkingBoyTest {

  ParkingLot parkingLot1;
  ParkingLot parkingLot2;
  private List<ParkingLot> parkingLots;
  SmartParkingBoy smartParkingBoy;

  @BeforeEach
  void setUp() {
    parkingLot1 = new ParkingLot(1);
    parkingLot2 = new ParkingLot(2);
    parkingLots =  newArrayList(parkingLot1, parkingLot2);
    smartParkingBoy = new SmartParkingBoy(parkingLots);
  }

  @Test
  void should_return_ticket_and_verify_parking_car_to_parking_lot_has_more_space_when_park_to_parking_boy_given_a_car_and_parking_lots_has_different_space() {
    smartParkingBoy = new SmartParkingBoy(newArrayList(parkingLot1, parkingLot2));
    Car car = new Car(1);

    Ticket ticket = smartParkingBoy.park(car);

    assertNotNull(ticket);
    assertTrue(parkingLot2.hasCar(ticket));
  }

  @Test
  void should_return_ticket_and_verify_car_park_into_first_parking_lot_when_park_to_parking_boy_given_a_car_and_same_parking_space() {
    parkingLot2 = new ParkingLot(1);
    smartParkingBoy = new SmartParkingBoy(newArrayList(parkingLot1, parkingLot2));
    Car car = new Car(1);

    Ticket ticket = smartParkingBoy.park(car);

    assertNotNull(ticket);
    assertTrue(parkingLot1.hasCar(ticket));
  }

  @Test
  void should_return_car_when_fetch_given_valid_ticket() {
    Car parkingCar = new Car(1);
    Ticket ticket = smartParkingBoy.park(parkingCar);

    Car fetchedCar = smartParkingBoy.fetch(ticket);

    assertEquals(parkingCar, fetchedCar);
  }

  @Test
  void should_throw_exception_when_fetch_given_fake_ticket() {
    Car parkingCar = new Car(1);
    smartParkingBoy.park(parkingCar);
    Ticket ticket = new Ticket(2);

    assertThrows(InvalidTicketException.class, () -> smartParkingBoy.fetch(ticket));
  }

  @Test
  void should_throw_exception_when_fetch_given_no_ticket() {
    Car parkingCar = new Car(1);
    smartParkingBoy.park(parkingCar);

    assertThrows(InvalidTicketException.class, () -> smartParkingBoy.fetch(null));
  }

  @Test
  void should_return_ticket_when_park_to_smart_parking_boy_given_a_car_and_full_parking_lots_fetch_a_car() {
    Car car1 = new Car(1);
    Car car2 = new Car(2);
    Ticket ticket1 = smartParkingBoy.park(car1);
    smartParkingBoy.park(car2);
    smartParkingBoy.fetch(ticket1);
    Car car = new Car(3);

    Ticket ticket = smartParkingBoy.park(car);

    assertNotNull(ticket);
  }
}
