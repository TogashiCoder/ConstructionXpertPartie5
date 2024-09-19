package com.Taches.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rossource-service", url = "http://localhost:8083/api/Ressource")
public interface RessourceInterface {

    // Supprimer toutes les ressources liées à une tâche
    @DeleteMapping("/tache/{idTache}")
     ResponseEntity<Void> deleteRessourcesByTacheId(@PathVariable Long idTache) ;

}
