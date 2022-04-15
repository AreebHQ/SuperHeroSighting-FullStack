package com.sg.superhero.Controller;

import com.sg.superhero.Dao.LocationDao;
import com.sg.superhero.Dao.OrganizationDao;
import com.sg.superhero.Dao.SuperMemberDao;
import com.sg.superhero.entities.SuperMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SuperMemberController {

    @Autowired
    SuperMemberDao superMemberDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    @GetMapping("superMembers")
    public String displaySuperMembers(Model model)
    {
        List<SuperMember> superMembers = superMemberDao.getAllSuperMembers();
        model.addAttribute("superMembers",superMembers);
        return "superMembers";
    }

    @PostMapping("addSuperMember")
    public String addSuperMember(String name, String description, String superpower)
    {
        SuperMember superMember = new SuperMember();
        superMember.setName(name);
        superMember.setDescription(description);
        superMember.setSuperPower(superpower);
        superMemberDao.addSuperMember(superMember);
        return "redirect:/superMembers";
    }

    @PostMapping("deleteSuperMember")
    public String deleteSuperMember(Integer id)
    {
        System.out.println("id: " + id);
        superMemberDao.deleteSuperMemberById(id);
        return "redirect:/superMembers";
    }

    @GetMapping("editSuperMember")
    public String editSuperMember(Integer id, Model model)
    {
        SuperMember superMember = superMemberDao.getSuperMemberById(id);
        model.addAttribute("superMember",superMember);
        return "editSuperMember";
    }

    @PostMapping("editSuperMember")
    public String performEditSuperMember(@Valid SuperMember superMember, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "editSuperMember";
        }
        superMemberDao.updateSuperMember(superMember);
        return "redirect:/superMembers";
    }

}
