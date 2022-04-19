package com.sg.superhero.Dao;

import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.SuperMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SuperMemberDaoDB implements SuperMemberDao{

    @Autowired
    JdbcTemplate jdbc;


    public static final class SuperMemberMapper implements RowMapper<SuperMember>
    {

        @Override
        public SuperMember mapRow(ResultSet rs, int rowNum) throws SQLException {

            SuperMember superMember = new SuperMember();
            superMember.setId(rs.getInt("memberId"));
            superMember.setName(rs.getString("name"));
            superMember.setDescription(rs.getString("description"));
            superMember.setSuperPower(rs.getString("superpower"));

            return superMember;
        }
    }

    @Override
    @Transactional
    public SuperMember addSuperMember(SuperMember superMember) {
        final String INSERT_MEMBER = "INSERT INTO supermember(name,description,superpower) VALUES(?,?,?);";
        jdbc.update(INSERT_MEMBER,superMember.getName(),superMember.getDescription(),superMember.getSuperPower());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()",Integer.class);
        superMember.setId(newId);
        if(superMember.getOrganizations()!=null)
        { insertSuperMemberOrganization(superMember);}
        return superMember;
    }

    private void insertSuperMemberOrganization(SuperMember superMember) {
        final String INSERT_MEMBER_ORGANIZATIONS = "INSERT INTO member_organization(memberId,organizationId) VALUES(?,?);";
        for(Organization organization : superMember.getOrganizations())
        {
            jdbc.update(INSERT_MEMBER_ORGANIZATIONS,superMember.getId(),organization.getId());
        }
    }

    @Override
    public SuperMember getSuperMemberById(int id) {

        final String SELECT_MEMBER_BY_ID = "SELECT * FROM supermember WHERE memberId = ?";
        SuperMember superMember = jdbc.queryForObject(SELECT_MEMBER_BY_ID,new SuperMemberMapper(), id);
        superMember.setOrganizations(getOrganizationsForMember(id));
        return superMember;
    }

    @Override
    public List<Organization> getOrganizationsForMember(int id) {

        final String SELECT_ORGANIZATIONS_FOR_MEMBER = "SELECT org.* FROM organization org JOIN " +
                "member_organization s ON org.organizationId = s.organizationId WHERE s.memberId = ?" ;
        return jdbc.query(SELECT_ORGANIZATIONS_FOR_MEMBER,new OrganizationDaoDB.OrganizationMapper(),id);

    }

    @Override
    public List<SuperMember> getMembersForOrganization(int id) {

        final String SELECT_MEMBERS_FOR_ORGANIZATION = "SELECT s.* FROM supermember s JOIN" +
                " member_organization org ON org.memberId = s.memberId WHERE org.organizationId = ?";
        return jdbc.query(SELECT_MEMBERS_FOR_ORGANIZATION,new SuperMemberMapper(),id);

    }


    @Override
    public List<SuperMember> getAllSuperMembers() {
        final String SELECT_ALL_MEMBERS = "SELECT * FROM supermember";
        List<SuperMember> superMembers = jdbc.query(SELECT_ALL_MEMBERS,new SuperMemberMapper());
        associateOrganizationsForSuperMembers(superMembers);
        return superMembers;
    }

    private void associateOrganizationsForSuperMembers(List<SuperMember> superMembers) {
        for(SuperMember superMember : superMembers)
        {
            superMember.setOrganizations(getOrganizationsForMember(superMember.getId()));
        }
    }

    @Override
    @Transactional
    public void updateSuperMember(SuperMember superMember) {

        final String UPDATE_MEMBER = "UPDATE supermember SET name = ?, description = ?, superpower = ? WHERE memberId = ?";
        jdbc.update(UPDATE_MEMBER,superMember.getName(),superMember.getDescription(),superMember.getSuperPower(),superMember.getId());

        final String DELETE_MEMBER_ORGANIZATION = "DELETE FROM member_organization WHERE memberId = ?";
        jdbc.update(DELETE_MEMBER_ORGANIZATION,superMember.getId());
        if(superMember.getOrganizations()!=null)
        {insertSuperMemberOrganization(superMember);}

    }

    @Override
    @Transactional
    public void deleteSuperMemberById(int id) {

        final String DELETE_MEMBER_ORGANIZATION = "DELETE FROM member_organization WHERE memberId = ?";
        jdbc.update(DELETE_MEMBER_ORGANIZATION, id);

        final String DELETE_MEMBER = "DELETE FROM supermember WHERE memberId = ?";
        jdbc.update(DELETE_MEMBER,id);
    }
}
