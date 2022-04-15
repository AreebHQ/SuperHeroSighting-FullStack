package com.sg.superhero.Dao;

import com.sg.superhero.entities.Organization;

import java.util.List;

public interface OrganizationDao {
    Organization addOrganization(Organization organization);
    Organization getOrganizationById(int id);
    List<Organization> getAllOrganizations();
    void updateOrganization (Organization organization);
    void deleteOrganization (int id);

}
