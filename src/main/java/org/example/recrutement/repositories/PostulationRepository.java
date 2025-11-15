package org.example.recrutement.repositories;

import org.example.recrutement.entities.Postulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulationRepository extends JpaRepository<Postulation, Long> {
    // Vérifie si une postulation existe pour un user et une offre donnés

    List<Postulation> findByUserIdAndOffreId(Long userId, Long offreId);

    // Récupère les postulations pour les offres d'un client selon le statut de l'offre et de la postulation

    List<Postulation> findByOffreUserIdAndOffreStatusAndStatus(Long userId, String offreStatus, String postulationStatus);

    // Récupère toutes les postulations faites par un jobber

    List<Postulation> findByUserId(Long userId);

    // Récupère les postulations d'un jobber selon le statut

    List<Postulation> findByUserIdAndStatus(Long userId, String status);

    // Récupère toutes les postulations reçues par un client

    List<Postulation> findByOffreUserId(Long userId);
}
