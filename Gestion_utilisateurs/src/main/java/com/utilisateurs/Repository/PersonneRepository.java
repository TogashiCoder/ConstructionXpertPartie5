package com.utilisateurs.Repository;



import com.utilisateurs.Model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
    Optional<Personne> findByusername(String username);
}
