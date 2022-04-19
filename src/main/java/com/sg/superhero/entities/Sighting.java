package com.sg.superhero.entities;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Sighting {

    int id;
    int memberId;
    int locationId;

    Location location;

    SuperMember superMember;
    @NotBlank(message = "Date must not be empty.")
    String date;


    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public SuperMember getSuperMember() {
        return superMember;
    }

    public void setSuperMember(SuperMember superMember) {
        this.superMember = superMember;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return id == sighting.id && memberId == sighting.memberId && locationId == sighting.locationId && Objects.equals(location, sighting.location) && Objects.equals(superMember, sighting.superMember) && Objects.equals(date, sighting.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, locationId, location, superMember, date);
    }
}
