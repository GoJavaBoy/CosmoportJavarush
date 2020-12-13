package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;

import java.util.List;

public interface ShipService {
    Ship addShip(Ship ship);

    Ship updateShip(Ship ship, Long id);

    void removeShip(Long id);

    Ship getShipById(Long id);

    List<Ship> shipsSortedList(String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed,
                         Double minSpeed, Double maxSpeed, Integer minCrewSize,
                         Integer maxCrewSize, Double minRaiting, Double maxRaiting);

    List<Ship> shipsFromPage(List<Ship> allSortedShips, ShipOrder order,
                              Integer pageNumber, Integer pageSize);
}
