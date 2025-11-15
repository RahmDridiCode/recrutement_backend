package org.example.recrutement.controllers;

import org.example.recrutement.entities.Organisation;
import org.example.recrutement.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrganisationController {

    @Autowired
    OrganisationService organisationService;

    @PostMapping(path = "/organisation/create")
    public Organisation creatOrganisation(@RequestBody Organisation organisation ){
        return organisationService.creatOrganisation(organisation);
    }


    @PostMapping(path = "/organisation/update")
    public Organisation updateOrganisation(@RequestBody Organisation organisation ){
        return organisationService.updateOrganisation(organisation);
    }

    @GetMapping(path = "/organisation/list")
    public List<Organisation> listOrganisation(){
        return organisationService.listOrganisation();
    }

   /*@DeleteMapping(path = "/organisation/delete/{organisationId}")
   public  String deleteOrganisation(){
       organisationService.deleteOrganisation(organisationId);
       return "organisation deleted";
   }*/



    @DeleteMapping(path = "/organisation/delete/{organisationID}")
    public String deleteOrganisation(@PathVariable(name = "organisationID")Long organisationID){
        organisationService.deleteOrganisation(organisationID);
        return "organisation deleted";
    }

}

