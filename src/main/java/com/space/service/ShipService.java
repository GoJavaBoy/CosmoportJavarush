package com.space.service;

import com.space.model.Ship;

import java.util.List;

public interface ShipService {
    void addShip(Ship ship);

    void updateShip(Ship ship);

    void removeShip(int id);

    Ship getShipById(int id);

    List<Ship> shipsList();
}
