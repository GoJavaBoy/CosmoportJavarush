package com.space.service;

import com.space.controller.ShipOrder;
import com.space.exceptions.BadRequestException;
import com.space.exceptions.NotFoundException;
import com.space.model.ShipType;
import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository shipRepository;

//    @Autowired
//    public void setShipDao(ShipDao shipDao) {
//        this.shipDao = shipDao;
//    }

    @Override
    @Transactional
    public Ship addShip(Ship ship) {
      //  shipRepository.addShip(ship);
        if (ship.getName()==null || ship.getPlanet()==null || ship.getShipType()==null
                || ship.getProdDate()==null || ship.getSpeed()==null || ship.getCrewSize()==null){
            throw new BadRequestException();
        }
        if (ship.getName().length()>50 || ship.getPlanet().length()>50
                || ship.getName().isEmpty() || ship.getPlanet().isEmpty()){
            throw new BadRequestException();
        }
        if (ship.getSpeed()<0.01d || ship.getSpeed()>0.99d || ship.getCrewSize()<1 || ship.getCrewSize()>9999){
            throw new BadRequestException();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ship.getProdDate());
        if (calendar.getTimeInMillis()<0) {
            throw new BadRequestException();
        }
        if (calendar.get(Calendar.YEAR) < 2800 || calendar.get(Calendar.YEAR) > 3019){
            throw new BadRequestException();
        }


        if (ship.getUsed()==null){
            ship.setUsed(false);
        }
        ship.setRating(raitingCalculator(ship));
        shipRepository.save(ship);
        return ship;
    }

    @Override
    @Transactional
    public Ship updateShip(Ship ship, Long id) {
     //   shipDao.updateShip(ship);
        if (!isValidId(id)){
            throw new BadRequestException();
        }
        if (!shipRepository.existsById(id)){
            throw new NotFoundException();
        }

        Ship oldShip = getShipById(id);

        if (ship.getName()!=null){
            if (ship.getName().length()>50 || ship.getName().isEmpty()){
                throw new BadRequestException();
            }
            oldShip.setName(ship.getName());
        }
        if (ship.getPlanet()!=null){
            if (ship.getPlanet().length()>50 || ship.getPlanet().isEmpty()){
                throw new BadRequestException();
            }
            oldShip.setPlanet(ship.getPlanet());
        }
        if (ship.getShipType()!=null){
            oldShip.setShipType(ship.getShipType());
        }
        if (ship.getProdDate()!=null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ship.getProdDate());
            if (calendar.get(Calendar.YEAR) < 2800 || calendar.get(Calendar.YEAR) > 3019){
                throw new BadRequestException();
            }
            oldShip.setProdDate(ship.getProdDate());
        }
        if (ship.getUsed()!=null){
            oldShip.setUsed(ship.getUsed());
        }
        if (ship.getSpeed()!=null){
            if (ship.getSpeed()<0.01d || ship.getSpeed()>0.99d){
                throw new BadRequestException();
            }
            oldShip.setSpeed(ship.getSpeed());
        }
        if (ship.getCrewSize()!=null){
            if (ship.getCrewSize()<1 || ship.getCrewSize()>9999){
                throw new BadRequestException();
            }
            oldShip.setCrewSize(ship.getCrewSize());
        }

        oldShip.setRating(raitingCalculator(oldShip));
        return oldShip;
    }

    @Override
    @Transactional
    public void removeShip(Long id) {
      //  shipDao.removeShip(id);
        if (!isValidId(id)){
            throw new BadRequestException();
        }
        if (!shipRepository.existsById(id)){
            throw new NotFoundException();
        }
        shipRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Ship getShipById(Long id) {
        if (!isValidId(id)){
            throw new BadRequestException();
        }
        if (!shipRepository.existsById(id)){
            throw new NotFoundException();
        }
        return shipRepository.findById(id).orElse(null);// shipDao.getShipById(id);
    }

    @Override
    public List<Ship> shipsSortedList(String name, String planet, ShipType shipType, Long after,
                                      Long before, Boolean isUsed, Double minSpeed, Double maxSpeed,
                                      Integer minCrewSize, Integer maxCrewSize, Double minRating,
                                      Double maxRating) {

        List<Ship> allShips = shipRepository.findAll();
        if (name!=null){
            allShips = allShips.stream()
                    .filter(ship -> ship.getName().contains(name))
                    .collect(Collectors.toList());
        }
        if (planet!=null){
            allShips = allShips.stream()
                    .filter(ship -> ship.getPlanet().contains(planet))
                    .collect(Collectors.toList());
        }
        if (shipType!=null){
            allShips = allShips.stream()
                    .filter(ship -> ship.getShipType().equals(shipType))
                    .collect(Collectors.toList());
        }
        if (after!=null){
            allShips = allShips.stream()
                    .filter(ship -> ship.getProdDate().after(new Date(after)))
                    .collect(Collectors.toList());
        }
        if (before!=null){
            allShips = allShips.stream()
                    .filter(ship -> ship.getProdDate().before(new Date(before)))
                    .collect(Collectors.toList());
        }
        if (isUsed!=null){
            allShips = allShips.stream()
                    .filter(ship -> ship.getUsed().equals(isUsed))
                    .collect(Collectors.toList());
        }
        if (minSpeed!=null){
            allShips = allShips.stream()
                    .filter(ship -> ship.getSpeed()>=minSpeed)
                    .collect(Collectors.toList());
        }
        if (maxSpeed!=null){
            allShips = allShips.stream()
                    .filter(ship -> ship.getSpeed()<=maxSpeed)
                    .collect(Collectors.toList());
        }
        if (minCrewSize!=null){
            allShips = allShips.stream()
                    .filter(ship -> ship.getCrewSize()>=minCrewSize)
                    .collect(Collectors.toList());
        }
        if (maxCrewSize!=null){
            allShips = allShips.stream()
                    .filter(ship -> ship.getCrewSize()<=maxCrewSize)
                    .collect(Collectors.toList());
        }
        if (minRating!=null){
            allShips = allShips.stream()
                    .filter(ship -> ship.getRating()>=minRating)
                    .collect(Collectors.toList());
        }
        if (maxRating!=null){
            allShips = allShips.stream()
                    .filter(ship -> ship.getRating()<=maxRating)
                    .collect(Collectors.toList());
        }

        return allShips;
    }

    @Override
    public List<Ship> shipsFromPage(List<Ship> allSortedShips, ShipOrder order, Integer pageNumber, Integer pageSize) {
        if (pageNumber==null){
            pageNumber = 0;
        }
        if (pageSize==null){
            pageSize = 3;
        }
        Comparator<Ship> comparator;
        if (order == null) {
            comparator =  Comparator.comparing(Ship::getId);
        } else {
        switch (order.getFieldName()) {
            case "id":
                comparator = Comparator.comparing(Ship::getId);
                break;
            case "speed":
                comparator = Comparator.comparing(Ship::getSpeed);
                break;
            case "prodDate":
                comparator = Comparator.comparing(Ship::getProdDate);
                break;
            case "rating":
                comparator = Comparator.comparing(Ship::getRating);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + order.getFieldName() + " Comparator may null.");
            }
        }
        return allSortedShips.stream()
                .sorted(comparator)
                .skip(pageNumber*pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }


    //Additional methods
    private Double raitingCalculator(Ship ship){
        double k = ship.getUsed() ? 0.5d : 1.0d;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ship.getProdDate());
        double prodDate = calendar.get(Calendar.YEAR);
        double resultDouble = (80*ship.getSpeed()*k)/(3019-prodDate+1);
        return (double) Math.round(resultDouble * 100) / 100;

    }

    private Boolean isValidId(Long id) {
        return id != null &&
                id > 0 &&
                id == Math.floor(id);
    }
}
