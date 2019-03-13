package com.thoughtworks.bootcamp.parkingLot;

import com.thoughtworks.bootcamp.exceptions.ParkingForbidException;

import java.util.List;

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
}
