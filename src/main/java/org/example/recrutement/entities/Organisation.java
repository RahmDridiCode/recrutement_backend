package org.example.recrutement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long OrgId;
    private String NameOrg;
    private String DescOrg;
    private String LocaOgr;
}
