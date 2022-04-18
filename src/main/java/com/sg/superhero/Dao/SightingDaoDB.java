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
            sighting.setId(rs.getInt("sightingId"));
            sighting.setMemberId(rs.getInt("memberId"));
            sighting.setLocationId(rs.getInt("locationId"));
            sighting.setDate(rs.getString("date"));

            return sighting;
        }
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING ="INSERT INTO sighting(memberId, locationId, date) VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING,sighting.getMemberId(), sighting.getLocationId(),sighting.getDate());

        return sighting;
    }

    @Override
    public Sighting getSightingById(int memberId, int locationId) {
        final String SELECT_SIGHTING =  "SELECT * FROM sighting WHERE memberId = ? AND locationId = ?";
        Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING,new SightingMapping(),memberId,locationId);
        associateMemberAndLocation(sighting);
        return sighting;
    }


    @Override
    public Sighting getSightingById(int id) {
        final String SELECT_SIGHTING =  "SELECT * FROM sighting WHERE sightingId = ?";
        Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING,new SightingMapping(),id);
        associateMemberAndLocation(sighting);
        return sighting;
    }

    private void associateMemberAndLocation(Sighting sighting) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT * FROM location WHERE locationId = ?";
        sighting.setLocation(jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationDaoDB.LocationMapper(),sighting.getLocationId()));

        final String SELECT_MEMBER_FOR_SIGHTING = "SELECT * FROM supermember WHERE memberId = ?";
        sighting.setSuperMember(jdbc.queryForObject(SELECT_MEMBER_FOR_SIGHTING, new SuperMemberDaoDB.SuperMemberMapper(),sighting.getMemberId()));
    }



    @Override
    public List<Sighting> getAllSighting() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sighting ORDER BY date DESC";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS,new SightingMapping());
        // associate location and member
        for(Sighting sighting : sightings)
        {
            associateMemberAndLocation(sighting);
        }
        return sightings;

    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING= "UPDATE sighting SET memberId = ?, locationId = ?, date = ? WHERE sightingId = ?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getMemberId(),
                sighting.getLocationId(),
                sighting.getDate(),
                sighting.getId());
    }

    @Override
    public void deleteSighting(int memberId, int locationId) {
        final String DELETE_SIGHTING =  "DELETE FROM sighting WHERE locationId = ? AND memberId = ?";
        jdbc.update(DELETE_SIGHTING,locationId,memberId);
    }

    @Override
    public List<Sighting> getSightingForSuperMember(int id) {

        final String SELECT_MEMBER_FOR_SIGHTING = "SELECT * FROM sighting WHERE memberId = ?";
        List<Sighting> sightings = jdbc.query(SELECT_MEMBER_FOR_SIGHTING,new SightingMapping(),id);

        return sightings;
    }

    @Override
    public List<Sighting> getSightingForLocation(int id) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT * FROM sighting WHERE locationId = ?";
        List<Sighting> sightings = jdbc.query(SELECT_LOCATION_FOR_SIGHTING,new SightingMapping(),id);

        return sightings;
    }
}
