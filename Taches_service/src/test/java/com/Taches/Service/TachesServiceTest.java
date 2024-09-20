package com.Taches.Service;

import com.Taches.Dto.TachesDto;
import com.Taches.Feign.ProjetInterface;
import com.Taches.Feign.RessourceInterface;
import com.Taches.Model.Taches;
import com.Taches.Repository.TacheRepository;
import com.Taches.Response.ProjetResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TachesServiceTest {


    @InjectMocks
    private TachesService tachesService;

    @Mock
    private TacheRepository tacheRepository;

    @Mock
    private ProjetInterface projetInterface;

    @Mock
    private RessourceInterface ressourceInterface;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void createTache_ShouldReturnTachesDto_WhenProjectExists() {
        // Arrange
        TachesDto tachesDto = new TachesDto();
        tachesDto.setNom("Test Task");
        ProjetResponse projetResponse = new ProjetResponse();
        projetResponse.setId(1L);

        when(projetInterface.getProjectById(1L)).thenReturn(projetResponse);

        Taches savedTache = new Taches();
        savedTache.setId(1L);
        savedTache.setNom("Test Task");

        when(tacheRepository.save(any(Taches.class))).thenReturn(savedTache);

        TachesDto result = tachesService.createTache(tachesDto, 1L);

        assertNotNull(result);
        assertEquals("Test Task", result.getNom());
        verify(tacheRepository).save(any(Taches.class));
    }

    @Test
    void createTache_ShouldThrowException_WhenProjectDoesNotExist() {
        TachesDto tachesDto = new TachesDto();

        when(projetInterface.getProjectById(1L)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tachesService.createTache(tachesDto, 1L);
        });
        assertEquals("Projet non trouvé : ID 1", exception.getMessage());
    }

    @Test
    void getTachesById_ShouldReturnTachesDto_WhenTaskExists() {
        Taches existingTache = new Taches();
        existingTache.setId(1L);
        existingTache.setNom("Test Task");

        when(tacheRepository.findById(1L)).thenReturn(Optional.of(existingTache));

        TachesDto result = tachesService.getTachesById(1L);

        assertNotNull(result);
        assertEquals("Test Task", result.getNom());
    }

    @Test
    void getTachesById_ShouldReturnNull_WhenTaskDoesNotExist() {
        when(tacheRepository.findById(1L)).thenReturn(Optional.empty());
        TachesDto result = tachesService.getTachesById(1L);
        assertNull(result);
    }

    @Test
    void deleteTache_ShouldDeleteTaskAndRelatedResources() {
        Long taskId = 1L;
        tachesService.deleteTache(taskId);
        verify(ressourceInterface).deleteRessourcesByTacheId(taskId);
        verify(tacheRepository).deleteById(taskId);
    }

    @Test
    void deleteTache_ShouldThrowException_WhenResourceDeletionFails() {
        Long taskId = 1L;
        doThrow(new RuntimeException("Deletion error")).when(ressourceInterface).deleteRessourcesByTacheId(taskId);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            tachesService.deleteTache(taskId);
        });
        assertEquals("Erreur lors de la suppression des ressource pour l'ID du  tache 1", exception.getMessage());
    }




}