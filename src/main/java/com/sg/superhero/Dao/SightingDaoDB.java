package com.sg.superhero.Dao;

import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.Sighting;
import com.sg.superhero.entities.SuperMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SightingDaoDB implements SightingDao{

    @Autowired
    JdbcTemplate jdbc;

    public static final class SightingMapping implements RowMapper<Sighting>
    {

        @Override
        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {

            Sighting sighting = new Sighting();


            return null;
        }
    }


    @Override
    public Sighting addSighting(Sighting sighting) {
        return null;
    }

    @Override
    public Sighting getSightingById(int id) {
        return null;
    }

    @Override
    public List<Sighting> getAllSighting() {
        return null;
    }

    @Override
    public void updateSighting(Sighting sighting) {

    }

    @Override
    public void deleteSighting(int id) {

    }

    @Override
    public List<Sighting> getSightingForSuperMember(SuperMember superMember) {
        return null;
    }

    @Override
    public List<Sighting> getSightingForLocation(Location location) {
        return null;
    }
}
