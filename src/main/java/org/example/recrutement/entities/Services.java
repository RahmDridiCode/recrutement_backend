package org.example.recrutement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String category;
    private String name;
    private String image;
}
