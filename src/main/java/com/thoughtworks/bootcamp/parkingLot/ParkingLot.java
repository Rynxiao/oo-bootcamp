package com.thoughtworks.bootcamp.parkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
  private int size;
  private Map<Ticket, Car> carMap = new HashMap<>();

  public ParkingLot(int size) {
    this.size = size;
  }

  public ParkingLot(int size, Map carMap) {
    this.size = size;
    this.carMap = carMap;
  }

  public Boolean isFull() {
    return carMap.size() == this.size;
  }

  public Ticket park(boolean isFull, Car car) {
    if (isFull) {
      return null;
    }
    Ticket ticket = new Ticket(car.getNumber());
    carMap.put(ticket, car);
    return ticket;
  }

  public Car fetch(Ticket ticket) {
    return carMap.get(ticket);
  }
}
