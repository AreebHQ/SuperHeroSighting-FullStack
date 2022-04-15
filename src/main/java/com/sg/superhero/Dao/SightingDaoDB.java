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
            sighting.setMemberId(rs.getInt("memberId"));
            sighting.setLocationId(rs.getInt("locationId"));
            sighting.setDate(rs.getString("date"));

            return sighting;
        }
    }

    /*SELECT supermember.name,location.name, sighting.date FROM supermember JOIN
    sighting ON supermember.memberId=sighting.memberId JOIN
     location on location.locationId = sighting.locationId;
     */

    /*@Override
    public List<SuperMember> getMembersForOrganization(int id) {

        final String SELECT_MEMBERS_FOR_ORGANIZATION = "SELECT s.* FROM supermember s JOIN" +
                " member_organization org ON org.memberId = s.memberId WHERE org.organizationId = ?";
        return jdbc.query(SELECT_MEMBERS_FOR_ORGANIZATION,new SuperMemberDaoDB.SuperMemberMapper(),id);

    }*/

    @Override
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING ="INSERT INTO sighting(memberId, locationId, date) VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING,sighting.getMemberId(), sighting.getLocationId(),sighting.getDate());

        return sighting;
    }

    @Override
    public Sighting getSightingById(int id) {
        return null;
    }

    @Override
    public List<Sighting> getAllSighting() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sighting";
        return jdbc.query(SELECT_ALL_SIGHTINGS,new SightingMapping());
        //need to associate location and member
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_ORG = "UPDATE sighting SET memberId = ?, locationId = ?, date = ? WHERE organizationId = ?";
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
