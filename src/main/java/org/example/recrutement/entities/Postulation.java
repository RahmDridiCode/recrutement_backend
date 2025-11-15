package org.example.recrutement.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Postulation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cv;

    private String motivation;

    @Column(columnDefinition = "varchar(255) default 'en attente'")
    private String status;

    @ManyToOne(cascade= CascadeType.ALL)
    private User user;

    @ManyToOne(cascade=CascadeType.ALL)
    private Offre offre;
}
