package org.example.recrutement.repositories;

import org.example.recrutement.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {

}
