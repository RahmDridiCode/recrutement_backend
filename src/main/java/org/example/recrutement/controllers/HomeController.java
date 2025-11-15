package org.example.recrutement.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping(path = "/home")
    public String Home(@RequestParam(name="firstname")String firstname, @RequestParam(name="lastname")String lastname){
        return "Hello world" +firstname +" "+lastname;
    }
    @PostMapping(path = "/home2 ")
    public String Home2(@RequestParam(name="firstname")String firstname,@RequestParam(name="lastname")String lastname){
        return "Hello world" +firstname +" "+lastname;
    }
}
