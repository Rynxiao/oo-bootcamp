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
        .filter(parkingLot -> parkingLot.isCarExist(ticket))
        .findAny()
        .orElseThrow(InvalidTicketException::new)
        .fetch(ticket);
  }

  @Override
  public Boolean isFull() {
    return parkingLots.stream().allMatch(ParkingLot::isFull);
  }
}
