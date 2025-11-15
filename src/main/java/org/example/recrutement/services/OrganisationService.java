package org.example.recrutement.services;

import org.example.recrutement.entities.Organisation;

import java.util.List;

public interface OrganisationService {
    public Organisation creatOrganisation(Organisation Organisation);
    public Organisation updateOrganisation(Organisation Organisation);
    public void deleteOrganisation(Long OrgId);
    public List<Organisation> listOrganisation();
}
