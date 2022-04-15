package com.sg.superhero.entities;

import java.util.List;

public class Location {
    int id;
    String name;
    String description;
    String street;
    String city;
    String state;
    double coordinates;
    List<SuperMember> superMembers;

    public List<SuperMember> getSuperMembers() {
        return superMembers;
    }

    public void setSuperMembers(List<SuperMember> superMembers) {
        this.superMembers = superMembers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double coordinates) {
        this.coordinates = coordinates;
    }
}
