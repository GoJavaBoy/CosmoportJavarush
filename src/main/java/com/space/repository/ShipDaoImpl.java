//package com.space.repository;
//
//import com.space.model.Ship;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//@Repository
//public class ShipDaoImpl implements ShipDao{
//    private static final Logger logger = LoggerFactory.getLogger(ShipDaoImpl.class);
//
//    private EntityManager entityManager;
//
//    @Autowired
//    public void setSessionFactory(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    public void addShip(Ship ship) {
//        entityManager.persist(ship);
//        logger.info("Ship successfully added to the database. Ship details: " + ship);
//    }
//
//    @Override
//    public void updateShip(Ship ship) {
//        entityManager.merge(ship);
//        logger.info("Ship successfully updated in database. Ship details: " + ship);
//    }
//
//    @Override
//    public void removeShip(long id) {
//        Ship ship =entityManager.find(Ship.class, id);
//        if (ship!=null){
//            entityManager.remove(ship);
//            logger.info("Ship successfully deleted from database. Ship details: " + ship);
//        } else
//            logger.info("Ship with id: " + id + " do not exist in the database.");
//    }
//
//    @Override
//    public Ship getShipById(long id) {
//        Ship ship = entityManager.find(Ship.class, id);
//        if (ship!=null){
//            logger.info("Ship successfully loaded from database. Ship details: " + ship);
//            return ship;
//        } else
//            logger.info("Ship with id: " + id + " do not exist in the database.");
//        return null;
//    }
//
//    @Override
//    @SuppressWarnings("Unchecked")
//    public List<Ship> shipsList() {
//       // List<Ship> shipList = entityManager.createQuery("Select * from Ship").getResultList();
//        List<Ship> shipList = entityManager.createQuery("Select e from Ship e").getResultList();
//
//        for (Ship ship : shipList){
//            logger.info("Ship list: " + ship);
//        }
//        return shipList;
//
//    }
//}
//
