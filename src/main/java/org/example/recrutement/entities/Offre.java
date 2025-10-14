package org.example.recrutement.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ville;
    private String address;
    private String date;
    private double prix;
    private String description;
    private String service;
    private String status;
    @ManyToOne(cascade= CascadeType.ALL)
    private User user;

}
