package com.sg.superhero.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class Organization {

    int id;
    @NotBlank(message = "Organization name must not be empty.")
    @Size(max = 30, message = "Organization name must be less than 50 characters.")
    String name;
    @NotBlank(message = "Description must not be empty.")
    @Size(max = 500, message = "Description must be less than 500 characters.")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(superMembers, that.superMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, street, city, state, superMembers);
    }
}
