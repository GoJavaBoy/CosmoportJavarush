package com.space.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "ship")
public class Ship {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "planet")
    private String planet;

    @Column(name = "shipType")
    @Enumerated(EnumType.STRING)
    private ShipType shipType;

    @Column(name = "prodDate")
    private Date prodDate;

    @Column(name = "isUsed")
    private Boolean isUsed;

    @Column(name = "speed")
    private Double speed;

    @Column(name = "crewSize")
    private Integer crewSize;

    @Column(name = "rating")
    private Double rating;

    public Ship() {
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Ship that = (Ship) o;
//        Calendar calendarThis = Calendar.getInstance();
//        calendarThis.setTimeInMillis(prodDate.getTime());
//        int prodYearThis = calendarThis.get(Calendar.YEAR);
//        Calendar calendarThat = Calendar.getInstance();
//        calendarThat.setTimeInMillis(that.prodDate.getTime());
//        int prodYearThat = calendarThat.get(Calendar.YEAR);
//        return Objects.equals(id, that.id) &&
//                Objects.equals(name, that.name) &&
//                Objects.equals(planet, that.planet) &&
//                shipType == that.shipType &&
//                Objects.equals(prodYearThis, prodYearThat) &&
//                Objects.equals(isUsed, that.isUsed) &&
//                Objects.equals(speed, that.speed) &&
//                Objects.equals(crewSize, that.crewSize) &&
//                Objects.equals(rating, that.rating);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, planet, shipType, prodDate, isUsed, speed, crewSize, rating);
//    }

//    @Override
//    public String toString() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(prodDate.getTime());
//        int prodYear = calendar.get(Calendar.YEAR);
//        return "Ship{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", prodYear=" + prodYear +
//                '}';
//    }


    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", shipType=" + shipType +
                ", prodDate=" + prodDate +
                ", isUsed=" + isUsed +
                ", speed=" + speed +
                ", crewSize=" + crewSize +
                ", rating=" + rating +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}
