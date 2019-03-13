package com.thoughtworks.bootcamp.parkingLot;

public interface ParkingInterface {
    Ticket park(Car car);

    Boolean isFull();
}
