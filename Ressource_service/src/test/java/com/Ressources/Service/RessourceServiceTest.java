package com.Ressources.Service;

import com.Ressources.Dto.RessourceDto;
import com.Ressources.Feign.TacheInterface;
import com.Ressources.Model.Ressource;
import com.Ressources.Repository.RessourceRepository;
import com.Ressources.Response.TacheResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
class RessourceServiceTest {
    @InjectMocks
    private RessourceService ressourceService;

    @Mock
    private RessourceRepository ressourceRepository;

    @Mock
    private TacheInterface tacheInterface;

    private RessourceDto ressourceDto;
    private Ressource ressource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ressourceDto = new RessourceDto(1L, "Test Ressource", "Type A", 10, 1L);
        ressource = new Ressource();
        ressource.setId(1L);
        ressource.setNom("Test Ressource");
        ressource.setTypee("Type A");
        ressource.setQuantite(10);
        ressource.setIdTache(1L);
    }

    @Test
    public void testCreateRessource() {
        when(tacheInterface.getTacheById(1L)).thenReturn(new TacheResponse());
        when(ressourceRepository.save(any(Ressource.class))).thenReturn(ressource);

        RessourceDto createdRessource = ressourceService.createRessource(ressourceDto, 1L);

        assertNotNull(createdRessource);
        assertEquals("Test Ressource", createdRessource.getNom());
        verify(ressourceRepository, times(1)).save(any(Ressource.class));
    }

    @Test
    public void testGetRessourceById() {
        when(ressourceRepository.findById(1L)).thenReturn(Optional.of(ressource));

        RessourceDto foundRessource = ressourceService.getRessourceById(1L);

        assertNotNull(foundRessource);
        assertEquals("Test Ressource", foundRessource.getNom());
    }

    @Test
    public void testGetRessourceByIdNotFound() {
        when(ressourceRepository.findById(1L)).thenReturn(Optional.empty());

        RessourceDto foundRessource = ressourceService.getRessourceById(1L);

        assertNull(foundRessource);
    }

    @Test
    public void testUpdateRessource() {
        when(ressourceRepository.findById(1L)).thenReturn(Optional.of(ressource));
        when(ressourceRepository.save(any(Ressource.class))).thenReturn(ressource);

        RessourceDto updatedRessource = ressourceService.updateRessource(1L, ressourceDto);

        assertNotNull(updatedRessource);
        assertEquals("Test Ressource", updatedRessource.getNom());
        verify(ressourceRepository, times(1)).save(any(Ressource.class));
    }

    @Test
    public void testUpdateRessourceNotFound() {
        when(ressourceRepository.findById(1L)).thenReturn(Optional.empty());

        RessourceDto updatedRessource = ressourceService.updateRessource(1L, ressourceDto);

        assertNull(updatedRessource);
    }

    @Test
    public void testDeleteRessource() {
        doNothing().when(ressourceRepository).deleteById(1L);

        ressourceService.deleteRessource(1L);

        verify(ressourceRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllRessources() {
        when(ressourceRepository.findAll()).thenReturn(List.of(ressource));

        List<RessourceDto> ressources = ressourceService.getAllRessource();

        assertNotNull(ressources);
        assertEquals(1, ressources.size());
        assertEquals("Test Ressource", ressources.get(0).getNom());
    }

    @Test
    public void testDeleteRessourceByTacheId() {
        when(ressourceRepository.findByIdTache(1L)).thenReturn(List.of(ressource));
        doNothing().when(ressourceRepository).deleteAll(anyList());

        ressourceService.deleteRessourceByTacheId(1L);

        verify(ressourceRepository, times(1)).deleteAll(anyList());
    }


}