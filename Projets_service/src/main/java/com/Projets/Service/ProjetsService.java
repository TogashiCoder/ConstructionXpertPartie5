package com.Projets.Service;

import com.Projets.Dto.ProjetsDTO;
import com.Projets.Feign.TachesInterface;
import com.Projets.Model.Projets;
import com.Projets.Repository.ProjectRepository;
import com.Projets.Response.TacheResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjetsService implements IProjetsService {

    @Autowired
    private ProjectRepository projetsRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TachesInterface tachesInterface;

    // Créer un nouveau projet
    @Override
    public ProjetsDTO createProject(ProjetsDTO projetsDTO) {
        // Log the incoming DTO
        System.out.println("Creating project with name: " + projetsDTO.getNom());

        // Convert DTO to entity manually
        Projets projet = new Projets();
        projet.setId(projetsDTO.getId());
        projet.setNom(projetsDTO.getNom());
        projet.setDescription(projetsDTO.getDescription());
        projet.setDate_debut(projetsDTO.getDate_debut());
        projet.setDate_fin(projetsDTO.getDate_fin());
        projet.setBudget(projetsDTO.getBudget());

        // Log the entity before saving
        System.out.println("Mapped project entity: " + projet);

        // Save the entity to the repository
        projet = projetsRepository.save(projet);

        // Create and return a new DTO with the saved entity's data
        ProjetsDTO savedProjetsDTO = new ProjetsDTO();
        savedProjetsDTO.setId(projet.getId());
        savedProjetsDTO.setNom(projet.getNom());
        savedProjetsDTO.setDescription(projet.getDescription());
        savedProjetsDTO.setDate_debut(projet.getDate_debut());
        savedProjetsDTO.setDate_fin(projet.getDate_fin());
        savedProjetsDTO.setBudget(projet.getBudget());

        return savedProjetsDTO;
    }

    // Afficher la liste des projets existants
    @Override
    public List<ProjetsDTO> getAllProjects() {
        List<Projets> projets = projetsRepository.findAll();
        return projets.stream().map(projet -> {
            ProjetsDTO dto = new ProjetsDTO();
            dto.setId(projet.getId());
            dto.setNom(projet.getNom());
            dto.setDescription(projet.getDescription());
            dto.setDate_debut(projet.getDate_debut());
            dto.setDate_fin(projet.getDate_fin());
            dto.setBudget(projet.getBudget());
            return dto;
        }).collect(Collectors.toList());
    }

    // Mettre à jour un projet existant
    @Override
    public ProjetsDTO updateProject(Long id, ProjetsDTO projetsDTO) {
        Projets existingProjet = projetsRepository.findById(id).orElse(null);
        if (existingProjet != null) {
            existingProjet.setNom(projetsDTO.getNom());
            existingProjet.setDescription(projetsDTO.getDescription());
            existingProjet.setDate_debut(projetsDTO.getDate_debut());
            existingProjet.setDate_fin(projetsDTO.getDate_fin());
            existingProjet.setBudget(projetsDTO.getBudget());

            projetsRepository.save(existingProjet);

            ProjetsDTO updatedProjetsDTO = new ProjetsDTO();
            updatedProjetsDTO.setId(existingProjet.getId());
            updatedProjetsDTO.setNom(existingProjet.getNom());
            updatedProjetsDTO.setDescription(existingProjet.getDescription());
            updatedProjetsDTO.setDate_debut(existingProjet.getDate_debut());
            updatedProjetsDTO.setDate_fin(existingProjet.getDate_fin());
            updatedProjetsDTO.setBudget(existingProjet.getBudget());

            return updatedProjetsDTO;
        }
        return null;
    }

    // Supprimer un projet existant
    public void deleteProjectTache(Long id) {

        try {
            // D'abord, supprimer les tâches liées à ce projet
            tachesInterface.deleteTachesByProjetId(id);
        } catch (Exception e) {
            throw new IllegalStateException("Erreur lors de la suppression des tâches pour l'ID du projet : " + id, e);
        }

        // Ensuite, supprimer le projet
        projetsRepository.deleteById(id);
    }

    // Récupérer un projet par ID
    @Override
    public ProjetsDTO getProjetById(Long id) {
        Optional<Projets> optionalProjet = projetsRepository.findById(id);
        if (optionalProjet.isPresent()) {
            Projets projet = optionalProjet.get();
            ProjetsDTO dto = new ProjetsDTO();
            dto.setId(projet.getId());
            dto.setNom(projet.getNom());
            dto.setDescription(projet.getDescription());
            dto.setDate_debut(projet.getDate_debut());
            dto.setDate_fin(projet.getDate_fin());
            dto.setBudget(projet.getBudget());
            return dto;
        }
        return null;
    }


   public void deleteProjet(Long id){
        projetsRepository.deleteById(id);
   }

}
