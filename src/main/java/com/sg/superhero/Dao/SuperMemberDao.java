package com.sg.superhero.Dao;

import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.SuperMember;

import java.util.List;

public interface SuperMemberDao {

    SuperMember addSuperMember(SuperMember superMember);
    SuperMember getSuperMemberById(int id);
    List<SuperMember> getAllSuperMembers();
    void updateSuperMember(SuperMember superMember);
    void deleteSuperMemberById(int id);

    List<Organization> getOrganizationsForMember(int id);
    List<SuperMember> getMembersForOrganization(int id);

}
