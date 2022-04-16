package com.sg.superhero.Dao;

import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.Sighting;
import com.sg.superhero.entities.SuperMember;

import java.util.List;

public interface SightingDao {
    Sighting addSighting(Sighting sighting);
    Sighting getSightingById(int id);
    List<Sighting> getAllSighting();
    void updateSighting(Sighting sighting);
    void deleteSighting(int id);

    List<Sighting> getSightingForSuperMember(int id);
    List<Sighting> getSightingForLocation(int id);

}
