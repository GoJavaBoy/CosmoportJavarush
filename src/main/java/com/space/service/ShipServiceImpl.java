package com.space.service;

import com.space.repository.ShipDao;
import com.space.model.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ShipServiceImpl implements ShipService {
    private ShipDao shipDao;

    @Autowired
    public void setShipDao(ShipDao shipDao) {
        this.shipDao = shipDao;
    }

    @Override
    @Transactional
    public void addShip(Ship ship) {
        shipDao.addShip(ship);
    }

    @Override
    @Transactional
    public void updateShip(Ship ship) {
        shipDao.updateShip(ship);
    }

    @Override
    @Transactional
    public void removeShip(long id) {
        shipDao.removeShip(id);
    }

    @Override
    @Transactional
    public Ship getShipById(long id) {
        return shipDao.getShipById(id);
    }

    @Override
    @Transactional
    public List<Ship> shipsList() {
        return shipDao.shipsList();
    }
}
