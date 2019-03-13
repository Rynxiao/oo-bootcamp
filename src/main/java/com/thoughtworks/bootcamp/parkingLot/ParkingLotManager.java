package com.thoughtworks.bootcamp.parkingLot;

import java.util.List;

public class ParkingLotManager {

  private List<ParkingBoy> parkingBoyList;

  public ParkingLotManager(List<ParkingBoy> parkingBoyList) {
    this.parkingBoyList = parkingBoyList;
  }

  public Ticket park(Car car) {
    return new Ticket(1);
  }
}
