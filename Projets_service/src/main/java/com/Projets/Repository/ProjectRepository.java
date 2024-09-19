package com.Projets.Repository;

import com.Projets.Model.Projets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Projets, Long> {
}
