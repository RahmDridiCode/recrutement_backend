package org.example.recrutement.controllers;

import org.example.recrutement.dto.PostulationRequest;
import org.example.recrutement.entities.Offre;
import org.example.recrutement.entities.Postulation;
import org.example.recrutement.entities.User;
import org.example.recrutement.repositories.OffreRepository;
import org.example.recrutement.repositories.PostulationRepository;
import org.example.recrutement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
public class PostulationController {

    @Autowired
    UserRepository userRepo;
    @Autowired
    PostulationRepository postulationRepo;
    @Autowired
    OffreRepository offreRepo;


    @PostMapping("/postulation/verify")
    public ResponseEntity<HashMap<String, String>> verifyPostulation(@RequestBody PostulationRequest request ){
        HashMap<String, String> responce = new HashMap<String, String>();
        List<Postulation> posulations = postulationRepo.findByUserIdAndOffreId(request.getUserId(), request.getOffreId());
        if(!posulations.isEmpty()) {
            responce.put("message", "Vous avez déja postulé dans cette offre!!");
            responce.put("failed", "true");
            return ResponseEntity.status(400).body(responce);
        }
        User user = userRepo.findById(request.getUserId()).get();
        if(user==null) {
            responce.put("message", "Un Erreur est Survenue!!");
            responce.put("failed", "true");
            return ResponseEntity.internalServerError().body(responce);
        }
        Offre offre = offreRepo.findById(request.getOffreId()).get();
        if(offre==null) {
            responce.put("message", "Un Erreur est Survenue!!");
            responce.put("failed", "true");
            return ResponseEntity.internalServerError().body(responce);
        }
        responce.put("message", "Vous n'avez pas postuler dans cette offre");
        responce.put("failed", "");
        return ResponseEntity.ok().body(responce);
    }







    //add postualtion
    @PostMapping("/postulation")
    public ResponseEntity<HashMap<String, String>> addPostulation(@RequestParam("offreId") String offreStr,
                                                                  @RequestParam("userId") String userStr,
                                                                  @RequestParam("cv") MultipartFile cv,
                                                                  @RequestParam("motivation") String motivation) throws IOException {

        Long userId = Long.parseLong(userStr);
        Long offreId = Long.parseLong(offreStr);
        HashMap<String, String> responce = new HashMap<String, String>();
        List<Postulation> posulations = postulationRepo.findByUserIdAndOffreId(userId,offreId);
        if(!posulations.isEmpty()) {
            responce.put("message", "Vous avez déja postulé dans cette offre!!");
            responce.put("failed", "true");
            return ResponseEntity.status(400).body(responce);
        }
        User user = userRepo.findById(userId).get();
        if(user==null) {
            responce.put("message", "Un Erreur est Survenue!!");
            responce.put("failed", "true");
            return ResponseEntity.internalServerError().body(responce);
        }
        Offre offre = offreRepo.findById(offreId).get();
        if(offre==null) {
            responce.put("message", "Un Erreur est Survenue!!");
            responce.put("failed", "true");
            return ResponseEntity.internalServerError().body(responce);
        }
        responce.put("message", "Vous avez postulé avec success");
        responce.put("failed", "");
        Postulation postulation = new Postulation();
        postulation.setOffre(offre);
        postulation.setUser(user);

        //save cv in assets !!
        File file = new File(System.getProperty("user.dir")).getCanonicalFile();
        System.out.println("Parent directory : " + file.getParent());
        File file2 = new File(file.getParent()+ "/frontend/src/assets/images/cvs/"+cv.getOriginalFilename());
        System.out.println("file directory : " + file2.getParent());
        file2.createNewFile();
        FileOutputStream fout = new FileOutputStream(file2);
        fout.write(cv.getBytes());
        postulation.setCv("assets/images/cvs/"+cv.getOriginalFilename());
        postulation.setMotivation(motivation);
        postulation.setStatus("pending");
        postulationRepo.save(postulation);
        return ResponseEntity.ok().body(responce);
    }
    //get all postulation pending of client
    @GetMapping("/postulation/{id}")
    public ResponseEntity<List<Postulation>> listAllPostulationsByUser(@PathVariable Long id){
        List<Postulation> postulations = postulationRepo.findByOffreUserId(id);
        return ResponseEntity.ok().body(postulations);
    }
    //get all postulation pending of client
    @GetMapping("/postulation/user/{id}")
    public ResponseEntity<List<Postulation>> listPostulations(@PathVariable Long id){
        List<Postulation> postulations = postulationRepo.findByOffreUserIdAndOffreStatusAndStatus(id,"pending","pending");
        return ResponseEntity.ok().body(postulations);
    }

    //get all postulation closed of client
    @GetMapping("/postulation/user/closed/{id}")
    public ResponseEntity<List<Postulation>> listPostulationsClosed(@PathVariable Long id){
        List<Postulation> postulations = postulationRepo.findByOffreUserIdAndOffreStatusAndStatus(id,"closed","accepted");
        return ResponseEntity.ok().body(postulations);
    }

    //accept postulation
    @GetMapping("/postulation/accept/{id}")
    public ResponseEntity<Postulation> getPostulationById(@PathVariable Long id){
        Postulation postulation = postulationRepo.findById(id).get();
        postulation.setStatus("accepted");
        postulationRepo.save(postulation);
        return ResponseEntity.ok().body(postulation);
    }


    //accept postulation
    @GetMapping("/postulation/etudiant/{id}")
    public ResponseEntity<List<Postulation>> getJobByJobberId(@PathVariable Long id){
        List<Postulation> postulations = postulationRepo.findByUserId(id);
        return ResponseEntity.ok().body(postulations);
    }
    //finish Mission
    @GetMapping("/postulation/finish/{id}")
    public ResponseEntity<Postulation> finishPostulation(@PathVariable Long id){
        Postulation postulation = postulationRepo.findById(id).get();
        postulation.setStatus("finished");
        postulationRepo.save(postulation);
        return ResponseEntity.ok().body(postulation);
    }
    @GetMapping("/postulation/finished/{id}")
    public ResponseEntity<List<Postulation>> getFinishedPostulation(@PathVariable Long id){
        List<Postulation> postulations = postulationRepo.findByUserIdAndStatus(id,"finished");
        return ResponseEntity.ok().body(postulations);
    }

}