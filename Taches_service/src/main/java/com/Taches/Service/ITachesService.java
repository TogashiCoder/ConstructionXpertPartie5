package com.Taches.Service;

import com.Taches.Dto.TachesDto;
import com.Taches.Model.Taches;

import java.util.List;

public interface ITachesService {

        TachesDto createTache(TachesDto tachesDto,  Long idProjet);
        //Taches createTache(Taches tachesDto, Long idProjet);
        TachesDto getTachesById(Long id);
        void deleteTache(Long id);

        // Afficher la liste des projets existants
        List<TachesDto> getAllTaches();

        TachesDto updateTache(Long id, TachesDto tachesDto);

        void deleteTachesByProjetId(Long idProjet);
}
