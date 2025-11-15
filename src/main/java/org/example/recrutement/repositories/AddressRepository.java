package org.example.recrutement.repositories;

import org.example.recrutement.entities.Addresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Addresse,Long> {
}
