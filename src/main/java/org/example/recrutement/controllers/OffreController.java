package org.example.recrutement.controllers;

import org.example.recrutement.entities.Offre;
import org.example.recrutement.entities.User;
import org.example.recrutement.repositories.UserRepository;
import org.example.recrutement.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@RestController

public class OffreController {
    @Autowired
    OffreService offreService;
    @Autowired
    UserRepository userRepo;

    @PostMapping(path = "/offre/create/{userId}")
    public Offre creatOffre(@RequestBody Offre offre, @PathVariable Long userId ){
        Optional<User> user = userRepo.findById(userId);

        if(user.isPresent()) {
            offre.setUser(user.get());
        }
        offre.setStatus("pending");

        return offreService.createOffre(offre);
    }


    @PostMapping(path = "/offre/update")
    public Offre updateOffre(@RequestBody Offre offre ){
        return offreService.updateOffre(offre);
    }

    @GetMapping(path = "/offre/list")
    public List<Offre> listOffre(){
        return offreService.listOffre();
    }

    @GetMapping(path = "/offre/listby/{id}")
    public  List<Offre> listOffreByUser(@PathVariable Long id){
        List<Offre> offre = offreService.listOffreByUser(id);
        return offre;
    }
    @GetMapping(path = "/offre/closed/{id}")
    public  Offre changeOffreStatus(@PathVariable Long id){
        Offre offre = offreService.changeStatus(id);
        return offre;
    }
/*
@GetMapping(path = "/offre/list/pending")
public  List<Offre> listOffreByStatus(){
    return offreService.listOffre();
}*/

/*@DeleteMapping(path = "/offre/delete/{offreId}")
public  String deleteOffre(){
    offreService.deleteOffre(offreId);
    return "offre deleted";
}*/

    @RequestMapping(value = "/offre/delete/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
    public HashMap<String, String> deleteOffre(@PathVariable(name = "id")Long id){
        offreService.deleteOffre(id);
        HashMap<String, String> responce = new HashMap<>();
        responce.put("message", "deleted succesfully!");
        return responce;
    }
}
