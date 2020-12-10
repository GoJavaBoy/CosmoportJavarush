package com.space.repository;

import com.space.model.Ship;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShipDaoImpl implements ShipDao{
    private static final Logger logger = LoggerFactory.getLogger(ShipDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addShip(Ship ship) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(ship);
        logger.info("Ship successfully added to the database. Ship details: " + ship);
    }

    @Override
    public void updateShip(Ship ship) {
        Session session = sessionFactory.getCurrentSession();
        session.update(ship);
        logger.info("Ship successfully updated in database. Ship details: " + ship);
    }

    @Override
    public void removeShip(int id) {
        Session session = sessionFactory.getCurrentSession();
        Ship ship = session.load(Ship.class, id);
        if (ship!=null){
            session.delete(ship);
            logger.info("Ship successfully deleted from database. Ship details: " + ship);
        } else
            logger.info("Ship with id: " + id + " do not exist in the database.");
    }

    @Override
    public Ship getShipById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Ship ship = session.load(Ship.class, id);
        if (ship!=null){
            logger.info("Ship successfully loaded from database. Ship details: " + ship);
            return ship;
        } else
            logger.info("Ship with id: " + id + " do not exist in the database.");
        return null;
    }

    @Override
    @SuppressWarnings("Unchecked")
    public List<Ship> shipsList() {
        Session session = sessionFactory.getCurrentSession();
        List<Ship> shipList = session.createQuery("from Ship").list();
        for (Ship ship : shipList){
            logger.info("Ship list: " + ship);
        }
        return shipList;

    }
}

