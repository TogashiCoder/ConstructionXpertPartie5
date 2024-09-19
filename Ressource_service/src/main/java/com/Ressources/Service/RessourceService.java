package com.Ressources.Service;

import com.Ressources.Dto.RessourceDto;
import com.Ressources.Feign.TacheInterface;
import com.Ressources.Model.Ressource;
import com.Ressources.Repository.RessourceRepository;
import com.Ressources.Response.TacheResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RessourceService implements  IRessourceService{


    @Autowired
    private RessourceRepository ressourceRepository;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private TacheInterface tacheInterface;

    @Override
    public RessourceDto createRessource(RessourceDto ressourceDto, Long idTache) {

        try {
            TacheResponse tacheResponse = tacheInterface.getTacheById(idTache);
            // Assure-toi que projetResponse n'est pas nul ici
            if (tacheResponse == null) {
                throw new IllegalArgumentException("Projet non trouvé : ID " + idTache);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Erreur lors de la récupération du projet : " + e.getMessage());
        }

        Ressource ressource = new Ressource();
        ressource.setNom(ressourceDto.getNom());
        ressource.setTypee(ressourceDto.getTypee());
        ressource.setQuantite(ressourceDto.getQuantite());
        ressource.setIdTache(idTache);

        Ressource savedRessource = ressourceRepository.save(ressource);

        RessourceDto resultDto = new RessourceDto();
        resultDto.setId(savedRessource.getId());
        resultDto.setNom(savedRessource.getNom());
        resultDto.setTypee(savedRessource.getTypee());
        resultDto.setQuantite(savedRessource.getQuantite());
        resultDto.setIdTache(savedRessource.getIdTache());

        return resultDto;
    }

    @Override
    public RessourceDto getRessourceById(Long id) {
        Optional<Ressource> optionalRessource = ressourceRepository.findById(id);
        if (optionalRessource.isPresent()) {
            Ressource ressource = optionalRessource.get();
            RessourceDto ressourceDto = new RessourceDto();
            ressourceDto.setId(ressource.getId());
            ressourceDto.setNom(ressource.getNom());
            ressourceDto.setTypee(ressource.getTypee());
            ressourceDto.setQuantite(ressource.getQuantite());
            ressourceDto.setIdTache(ressource.getIdTache());
            return ressourceDto;
        }
        return null;
    }

    @Override
    public void deleteRessource(Long id) {
        ressourceRepository.deleteById(id);
    }

    @Override
    public List<RessourceDto> getAllRessource() {
        List<Ressource> ressourceList = ressourceRepository.findAll();
        return ressourceList.stream().map(ressource -> {
            RessourceDto dto = new RessourceDto();
            dto.setId(ressource.getId());
            dto.setNom(ressource.getNom());
            dto.setTypee(ressource.getTypee());
            dto.setQuantite(ressource.getQuantite());
            dto.setIdTache(ressource.getIdTache());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public RessourceDto updateRessource(Long id, RessourceDto ressourceDto) {
        Optional<Ressource> optionalRessource = ressourceRepository.findById(id);
        if (optionalRessource.isPresent()) {
            Ressource existingRessource = optionalRessource.get();
            existingRessource.setNom(ressourceDto.getNom());
            existingRessource.setTypee(ressourceDto.getTypee());
            existingRessource.setQuantite(ressourceDto.getQuantite());
            existingRessource.setIdTache(ressourceDto.getIdTache());

            Ressource updatedRessource = ressourceRepository.save(existingRessource);

            RessourceDto resultDto = new RessourceDto();
            resultDto.setId(updatedRessource.getId());
            resultDto.setNom(updatedRessource.getNom());
            resultDto.setTypee(updatedRessource.getTypee());
            resultDto.setQuantite(updatedRessource.getQuantite());
            resultDto.setIdTache(updatedRessource.getIdTache());

            return resultDto;
        }
        return null;
    }

    @Override
    public void deleteRessourceByTacheId(Long idTache) {
        List<Ressource> ressources = ressourceRepository.findByIdTache(idTache);
        if (ressources != null && !ressources.isEmpty()) {
            ressourceRepository.deleteAll(ressources);
        }
    }
}
