package com.sg.superhero.Dao;

import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.SuperMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocationDaoDB implements LocationDao{

    @Autowired
    JdbcTemplate jdbc;

    public static final class LocationMapper implements RowMapper<Location>
    {

        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("locationId"));
            location.setName(rs.getString("name"));
            location.setDescription(rs.getString("description"));
            location.setStreet(rs.getString("street"));
            location.setCity(rs.getString("city"));
            location.setState(rs.getString("state"));
            location.setCoordinates(rs.getString("coordinates"));

            return location;
        }
    }
    @Override
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO location(name, description, street, city, state, coordinates)" +
                " VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getCoordinates());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setId(newId);
        insertSuperMember(location);
        return location;
    }

    private void insertSuperMember(Location location) {
        final String INSERT_MEMBER_LOCATION = "INSERT INTO sighting(locationId,memberId) VALUES(?,?)";
        for (SuperMember superMember : location.getSuperMembers())
        {
            jdbc.update(INSERT_MEMBER_LOCATION,location.getId(),superMember.getId());
        }
    }

    @Override
    public Location getLocationById(int id) {
        try {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM location WHERE locationId = ?";
            return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocation() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM location";
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public void updateLocation(Location location) {

        final String UPDATE_LOCATION = "UPDATE location SET name = ?, description = ?, street = ?, city = ?, state = ?, coordinates = ? " +
                "WHERE locationId = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getCoordinates(),
                location.getId());
    }

    @Override
    public void deleteLocationById(int id) {
        final String DELETE_SIGHTING = "DELETE FROM sighting WHERE locationId = ?";
        jdbc.update(DELETE_SIGHTING, id);

        final String DELETE_LOCATION = "DELETE FROM location WHERE locationId = ?";
        jdbc.update(DELETE_LOCATION, id);
    }

    @Override
    public List<Location> getSightingForSuperMember(SuperMember superMember) {
        final String SELECT_LOCATION_FOR_MEMBER = "SELECT * FROM sighting WHERE memberId = ?";
        List<Location> locations = jdbc.query(SELECT_LOCATION_FOR_MEMBER, new LocationMapper(), superMember.getId());
        associateLocationAndSuperMember(locations);

        return null;
    }

    private void associateLocationAndSuperMember(List<Location> locations) {
        for(Location location : locations)
        {
            location.setSuperMembers(getSuperMemberForLocation(location.getId()));
        }
    }

    private List<SuperMember> getSuperMemberForLocation(int id) {
        final String SELECT_MEMBER_FOR_LOCATION = "SELECT sm.* FROM supermember sm JOIN sighting s ON s.memberId = sm.memberId where s.locationId = ?";
        return jdbc.query(SELECT_MEMBER_FOR_LOCATION, new SuperMemberDaoDB.SuperMemberMapper(), id);
    }

    @Override
    public List<SuperMember> getSightingForLocation(Location location) {
        return null;
    }


}
