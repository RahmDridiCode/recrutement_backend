package org.example.recrutement.controllers;

import org.example.recrutement.entities.Services;
import org.example.recrutement.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/service")
@CrossOrigin(origins ="http://localhost:4200")
public class ServiceController {


    @Autowired
    ServiceService service;

    @GetMapping("")
    public ResponseEntity<List<Services>> listServices() {

        List<Services> services = service.listServices();
        return ResponseEntity.ok().body(services);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> deleteService(@PathVariable Long id) {
        service.deleteService(id);
        HashMap< String, String> responce = new HashMap<>();
        responce.put("message", "Deleted Successfull");
        return ResponseEntity.ok().body(responce);
    }

    @PostMapping("")
    public ResponseEntity<Services> addService(@RequestParam("image") MultipartFile image, @RequestParam("category") String category, @RequestParam("name") String name) throws IOException {
        File file = new File(System.getProperty("user.dir")).getCanonicalFile();
        System.out.println("Parent directory : " + file.getParent());
        File file2 = new File(file.getParent()+ "/frontend/src/assets/image/services/"+image.getOriginalFilename());
        file2.createNewFile();
        FileOutputStream fout = new FileOutputStream(file2);
        fout.write(image.getBytes());
        Services newService = new Services();
        newService.setCategory(category);
        newService.setName(name);
        newService.setImage("assets/image/services/"+image.getOriginalFilename());

        return ResponseEntity.ok().body(service.saveService(newService));

    }

}

