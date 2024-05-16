package com.airbnb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "nightly_price", nullable = false)
    private Integer nightlyPrice;

    @Column(name = "guest", nullable = false)
    private Integer guest;

    @Column(name = "bath_rooms", nullable = false)
    private Integer bathRooms;

    @Column(name = "bed_rooms", nullable = false)
    private Integer bedRooms;

    @Column(name = "property_name", nullable = false)
    private String propertyName;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getNightlyPrice() {
        return nightlyPrice;
    }

    public void setNightlyPrice(Integer nightlyPrice) {
        this.nightlyPrice = nightlyPrice;
    }

    public Integer getGuest() {
        return guest;
    }

    public void setGuest(Integer guest) {
        this.guest = guest;
    }

    public Integer getBathRooms() {
        return bathRooms;
    }

    public void setBathRooms(Integer bathRooms) {
        this.bathRooms = bathRooms;
    }

    public Integer getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(Integer bedRooms) {
        this.bedRooms = bedRooms;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}