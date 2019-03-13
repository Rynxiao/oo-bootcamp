package com.thoughtworks.bootcamp.parkingLot;

import com.thoughtworks.bootcamp.exceptions.InvalidTicketException;
import com.thoughtworks.bootcamp.exceptions.ParkingForbidException;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

public class ParkingLotManager {

  private List<ParkingInterface> parkingLotList;

  public ParkingLotManager(List<ParkingInterface> parkingLotList) {
    this.parkingLotList = parkingLotList;
  }

  public Ticket park(Car car) {
    return parkingLotList.stream()
        .filter(parkingBoy -> !parkingBoy.isFull())
        .findAny()
        .orElseThrow(ParkingForbidException::new)
        .park(car);
  }

  public Car fetch(Ticket ticket) {
    if (isEmpty(ticket)) {
      throw new InvalidTicketException();
    }
    return parkingLotList.stream()
        .filter(parkingBoy -> parkingBoy.hasCar(ticket))
        .findAny()
        .orElseThrow(InvalidTicketException::new)
        .fetch(ticket);
  }
}
