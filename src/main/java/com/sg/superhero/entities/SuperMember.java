package com.sg.superhero.entities;

import com.sun.jdi.request.StepRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class SuperMember {
    int id;
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 50, message = "Name must be less than 50 characters.")
    String name;
    @NotBlank(message = "Description must not be empty.")
    @Size(max = 100, message = "Description must be less than 500 characters.")
    String description;
    @NotBlank(message = "SuperPower must not be empty.")
    @Size(max = 50, message = "SuperPower must be less than 500 characters.")
    String superPower;
    List<Organization> organizations;

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuperPower() {
        return superPower;
    }

    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperMember that = (SuperMember) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(superPower, that.superPower) && Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, superPower, organizations);
    }
}
