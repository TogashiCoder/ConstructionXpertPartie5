package com.Taches.Repository;

import com.Taches.Model.Taches;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TacheRepository  extends JpaRepository<Taches, Long> {
    List<Taches> findByIdProjet(Long idProjet);
}
