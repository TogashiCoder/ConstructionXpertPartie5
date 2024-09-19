package com.Ressources.Controller;


import com.Ressources.Dto.RessourceDto;
import com.Ressources.Model.Ressource;
import com.Ressources.Service.IRessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Ressource")
public class RessourceController {

    @Autowired
    private IRessourceService ressourceService;

    // Créer une nouvelle ressource
    @PostMapping("/{idTache}")
    public ResponseEntity<RessourceDto> createRessource(@RequestBody RessourceDto ressourceDto, @PathVariable Long idTache) {
        RessourceDto createdRessource = ressourceService.createRessource(ressourceDto, idTache);
        return ResponseEntity.ok(createdRessource);
    }

    // Récupérer une ressource par ID
    @GetMapping("/{id}")
    public ResponseEntity<RessourceDto> getRessourceById(@PathVariable Long id) {
        RessourceDto ressourceDto = ressourceService.getRessourceById(id);
        if (ressourceDto != null) {
            return ResponseEntity.ok(ressourceDto);
        }
        return ResponseEntity.notFound().build();
    }

    // Afficher la liste des ressources existantes
    @GetMapping
    public ResponseEntity<List<RessourceDto>> getAllRessources() {
        List<RessourceDto> ressources = ressourceService.getAllRessource();
        return ResponseEntity.ok(ressources);
    }

    // Mettre à jour une ressource existante
    @PutMapping("/{id}")
    public ResponseEntity<RessourceDto> updateRessource(@PathVariable Long id, @RequestBody RessourceDto ressourceDto) {
        RessourceDto updatedRessource = ressourceService.updateRessource(id, ressourceDto);
        if (updatedRessource != null) {
            return ResponseEntity.ok(updatedRessource);
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer une ressource existante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRessource(@PathVariable Long id) {
        ressourceService.deleteRessource(id);
        return ResponseEntity.noContent().build();
    }

    // Supprimer toutes les ressources liées à une tâche
    @DeleteMapping("/tache/{idTache}")
    public ResponseEntity<Void> deleteRessourcesByTacheId(@PathVariable Long idTache) {
        ressourceService.deleteRessourceByTacheId(idTache);
        return ResponseEntity.noContent().build();
    }
}
