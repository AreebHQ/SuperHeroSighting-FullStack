package com.sg.superhero.Dao;

import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.Sighting;
import com.sg.superhero.entities.SuperMember;

import java.util.List;

public interface LocationDao {

    Location addLocation(Location location);
    Location getLocationById(int id);
    List<Location> getAllLocation();
    void updateLocation(Location location);
    void deleteLocationById(int id);

    List<Location> getSightingForSuperMember(SuperMember superMember);
    List<SuperMember> getSightingForLocation(Location location);
}
