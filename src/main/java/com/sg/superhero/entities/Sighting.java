package com.sg.superhero.entities;

public class Sighting {
    int id;
    Location location;
    SuperMember superMember;
    String date;

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
