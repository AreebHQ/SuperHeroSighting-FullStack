package com.sg.superhero.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class Location {
    int id;
    @NotBlank(message = "Location name must not be empty.")
    @Size(max = 50, message = "Location name must be less than 50 characters.")
    String name;
    @NotBlank(message = "Description must not be empty.")
    @Size(max = 100, message = "Description must be less than 100 characters.")
    String description;
    @Size(max = 50, message = "Street must be less than 50 characters.")
    @NotBlank(message = "Street must not be empty.")
    String street;
    @Size(max = 50, message = "City must be less than 50 characters.")
    @NotBlank(message = "City must not be empty.")
    String city;
    @Size(max = 50, message = "State must be less than 50 characters.")
    @NotBlank(message = "State must not be empty.")
    String state;
    @Size(max = 100, message = "Coordinates must be less than 100 characters.")
    @NotBlank(message = "Coordinates must not be empty.")
    String coordinates;

    List<SuperMember> superMembers;

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

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


}
