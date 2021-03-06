package com.thoughtworks.bootcamp.parkingLot;

import com.thoughtworks.bootcamp.exceptions.InvalidTicketException;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

public abstract class ParkingBoy implements ParkingInterface {

  List<ParkingLot> parkingLots;

  public ParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Car fetch(Ticket ticket) {
    if (isEmpty(ticket)) {
      throw new InvalidTicketException();
    }
    return parkingLots.stream()
        .filter(parkingLot -> parkingLot.hasCar(ticket))
        .findAny()
        .orElseThrow(InvalidTicketException::new)
        .fetch(ticket);
  }

  public abstract Ticket park(Car car);

  @Override
  public Boolean isFull() {
    return parkingLots.stream().allMatch(ParkingLot::isFull);
  }

  @Override
  public Boolean hasCar(Ticket ticket) {
      return parkingLots.stream().anyMatch(parkinglot -> parkinglot.hasCar(ticket));
  }
}
