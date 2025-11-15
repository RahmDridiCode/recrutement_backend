package org.example.recrutement.services.impl;

import org.example.recrutement.entities.Organisation;
import org.example.recrutement.repositories.OrganisationRepository;
import org.example.recrutement.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganisationServiceImpl implements OrganisationService {


    @Autowired
    private OrganisationRepository organisationRepository;

    @Override
    public Organisation creatOrganisation(Organisation organisation) {

        return organisationRepository.save(organisation);
    }

    @Override
    public Organisation updateOrganisation(Organisation organisation) {
        Optional<Organisation> existingOrganisation=organisationRepository.findById(organisation.getOrgId());
        if(existingOrganisation.isPresent()){

            return organisationRepository.save(organisation);
        }else
        {return null;
        }}
    @Override
    public void deleteOrganisation(Long orgId) {
        Organisation organisation =organisationRepository.findById(orgId).get();
        organisationRepository.delete(organisation);
    }

    @Override
    public List<Organisation> listOrganisation() {

        return organisationRepository.findAll();
    }

}

