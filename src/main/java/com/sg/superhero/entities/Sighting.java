package com.sg.superhero.entities;

public class Sighting {

    int id;
    int memberId;
    int locationId;
    Location location;
    SuperMember superMember;
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
}
