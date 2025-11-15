package org.example.recrutement.repositories;

import org.example.recrutement.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    long countByPostName(String postName);
}
