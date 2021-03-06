package com.sg.superhero.Dao;

import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.SuperMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrganizationDaoDB implements OrganizationDao{

    @Autowired
    JdbcTemplate jdbc;

    public static final class OrganizationMapper implements RowMapper<Organization>
    {

        @Override
        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization organization = new Organization();
            organization.setId(rs.getInt("organizationId"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setStreet(rs.getString("street"));
            organization.setCity(rs.getString("city"));
            organization.setState(rs.getString("state"));

            return organization;
        }
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String INSERT_ORG ="INSERT INTO organization(name, description, street, city, state) VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_ORG,organization.getName(),organization.getDescription(),organization.getStreet(),organization.getCity(),organization.getState());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()",Integer.class);
        organization.setId(newId);
        if(organization.getSuperMembers()!=null)
        {
            insertOrganizationMembers(organization);
        }

        return organization;
    }

    private void insertOrganizationMembers(Organization organization) {
        final String INSERT_ORGANIZATION_MEMBERS = "INSERT INTO member_organization(memberId,organizationId) VALUES(?,?);";
        for(SuperMember superMember : organization.getSuperMembers())
        {
            jdbc.update(INSERT_ORGANIZATION_MEMBERS,superMember.getId(),organization.getId());
        }
    }

    @Override
    public Organization getOrganizationById(int id) {
        try {
            final String SELECT_ORG_BY_ID = "SELECT * FROM organization WHERE organizationId = ?";
            Organization organization = jdbc.queryForObject(SELECT_ORG_BY_ID, new OrganizationMapper(), id);
            organization.setSuperMembers(getMembersForOrganization(id));
            return organization;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM organization";
        return jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());

    }

    @Override
    public void updateOrganization(Organization organization) {

        final String UPDATE_ORG = "UPDATE organization SET name = ?, description = ?, street = ?, city = ?, state = ? WHERE organizationId = ?";
        jdbc.update(UPDATE_ORG,
                organization.getName(),
                organization.getDescription(),
                organization.getStreet(),
                organization.getCity(),
                organization.getState(),
                organization.getId());
    }

    @Override
    @Transactional
    public void deleteOrganization(int id) {

        final String DELETE_MEMBER_ORG = "DELETE FROM member_organization WHERE organizationId = ?";
        jdbc.update(DELETE_MEMBER_ORG,id);

        final String DELETE_ORG = "DELETE FROM organization WHERE organizationId = ?";
        jdbc.update(DELETE_ORG,id);
    }

    @Override
    public List<SuperMember> getMembersForOrganization(int id) {

        final String SELECT_MEMBERS_FOR_ORGANIZATION = "SELECT s.* FROM supermember s JOIN" +
                " member_organization org ON org.memberId = s.memberId WHERE org.organizationId = ?";
        return jdbc.query(SELECT_MEMBERS_FOR_ORGANIZATION,new SuperMemberDaoDB.SuperMemberMapper(),id);

    }
}
