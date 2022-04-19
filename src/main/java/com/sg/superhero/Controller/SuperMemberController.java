package com.sg.superhero.Controller;

import com.sg.superhero.Dao.LocationDao;
import com.sg.superhero.Dao.OrganizationDao;
import com.sg.superhero.Dao.SuperMemberDao;
import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.SuperMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SuperMemberController {

    @Autowired
    SuperMemberDao superMemberDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    Set<ConstraintViolation<SuperMember>> violations = new HashSet<>();

    @GetMapping("superMembers")
    public String displaySuperMembers(Model model)
    {
        List<SuperMember> superMembers = superMemberDao.getAllSuperMembers();
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("superMembers",superMembers);
        model.addAttribute("organizations",organizations);
        model.addAttribute("errors", violations);
        return "superMembers";
    }

    @PostMapping("addSuperMember")
    public String addSuperMember( HttpServletRequest request)
    {
        SuperMember superMember = new SuperMember();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String superpower = request.getParameter("superpower");
        superMember.setName(name);
        superMember.setDescription(description);
        superMember.setSuperPower(superpower);

        String[] organizationIds = request.getParameterValues("organizationId");
        if(organizationIds != null){
            List<Organization> organizations = new ArrayList<>();
            for(String organizationId : organizationIds)
            {
                organizations.add(organizationDao.getOrganizationById(Integer.parseInt(organizationId)));
                superMember.setOrganizations(organizations);
            }
        }


        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superMember);

        if(violations.isEmpty())
        {
            superMemberDao.addSuperMember(superMember);
        }

        return "redirect:/superMembers";
    }

    @GetMapping("deleteSuperMember")
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

    @GetMapping("superMemberDetail")
    public String superMemberDetail(Integer id, Model model)
    {
        SuperMember superMember = superMemberDao.getSuperMemberById(id);
        model.addAttribute("superMember",superMember);
        return "superMemberDetail";
    }

}
