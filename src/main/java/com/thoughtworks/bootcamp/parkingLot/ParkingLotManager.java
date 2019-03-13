package com.thoughtworks.bootcamp.parkingLot;

import com.thoughtworks.bootcamp.exceptions.InvalidTicketException;
import com.thoughtworks.bootcamp.exceptions.ParkingForbidException;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

public class ParkingLotManager {

  private List<ParkingInterface> parkingBoyList;

  public ParkingLotManager(List<ParkingInterface> parkingBoyList) {
    this.parkingBoyList = parkingBoyList;
  }

  public Ticket park(Car car) {
    return parkingBoyList.stream()
        .filter(parkingBoy -> !parkingBoy.isFull())
        .findAny()
        .orElseThrow(ParkingForbidException::new)
        .park(car);
  }

  public Car fetch(Ticket ticket) {
    if (isEmpty(ticket)) {
      throw new InvalidTicketException();
    }
    return parkingBoyList.stream()
        .filter(parkingBoy -> parkingBoy.hasCar(ticket))
        .findAny()
        .orElseThrow(InvalidTicketException::new)
        .fetch(ticket);
  }
}
