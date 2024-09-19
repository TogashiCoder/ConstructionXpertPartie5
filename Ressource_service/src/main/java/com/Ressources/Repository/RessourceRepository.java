package com.Ressources.Repository;

import com.Ressources.Model.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RessourceRepository extends JpaRepository<Ressource, Long> {

    List<Ressource> findByIdTache(Long idTache);
}
