package com.sg.superhero.Controller;

import com.sg.superhero.Dao.LocationDao;
import com.sg.superhero.Dao.OrganizationDao;
import com.sg.superhero.Dao.SuperMemberDao;
import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.SuperMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class OrganizationController {


    @Autowired
    SuperMemberDao superMemberDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    Set<ConstraintViolation<Organization>> violations = new HashSet<>();

    @GetMapping("organizations")
    public String displayOrganizations(Model model)
    {
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        model.addAttribute("errors", violations);
        return "organizations";
    }


    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request)
    {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");

        Organization organization = new Organization();
        organization.setName(name);
        organization.setDescription(description);
        organization.setStreet(street);
        organization.setCity(city);
        organization.setState(state);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);

        if(violations.isEmpty())
        {
            organizationDao.addOrganization(organization);
        }
        return "redirect:/organizations";
    }

    @PostMapping("deleteOrganization")
    public String deleteOrganization(HttpServletRequest request)
    {
        int id = Integer.parseInt(request.getParameter("id"));
        organizationDao.deleteOrganization(id);
        return "redirect:/organizations";
    }

    @GetMapping("editOrganization")
    public String editOrganization(HttpServletRequest request, Model model)
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Organization organization = organizationDao.getOrganizationById(id);
        model.addAttribute("organization",organization);
        return "editOrganization";
    }


    @PostMapping("editOrganization")
    public String performEditOrganization(HttpServletRequest request)
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Organization organization = organizationDao.getOrganizationById(id);

        organization.setName(request.getParameter("name"));
        organization.setDescription(request.getParameter("description"));
        organization.setStreet(request.getParameter("street"));
        organization.setCity(request.getParameter("city"));
        organization.setState(request.getParameter("state"));

        organizationDao.updateOrganization(organization);
        return "redirect:/organizations";

    }

}
