package com.space.repository;

import com.space.model.Ship;

import java.util.List;

public interface ShipDao {
    void addShip(Ship ship);

    void updateShip(Ship ship);

    void removeShip(int id);

    Ship getShipById(int id);

    List<Ship> shipsList();
}
