package org.example.recrutement.repositories;

import org.example.recrutement.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Long> {
    List<Offre> findByUser_Id(Long userId);
    List<Offre> findByStatus(String status);
}
