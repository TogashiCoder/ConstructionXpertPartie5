package com.Projets.Service;

import com.Projets.Dto.ProjetsDTO;
import com.Projets.Feign.TachesInterface;
import com.Projets.Model.Projets;
import com.Projets.Repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjetsServiceTest {


    @InjectMocks
    private ProjetsService projetsService;

    @Mock
    private ProjectRepository projetsRepository;

    @Mock
    private TachesInterface tachesInterface;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProject_ShouldReturnSavedProjectDTO() {
        ProjetsDTO projetsDTO = new ProjetsDTO();
        projetsDTO.setNom("Test Project");
        projetsDTO.setDescription("Test Description");
        projetsDTO.setBudget(1000.0);

        Projets savedProject = new Projets();
        savedProject.setId(1L);
        savedProject.setNom(projetsDTO.getNom());
        savedProject.setDescription(projetsDTO.getDescription());
        savedProject.setBudget(projetsDTO.getBudget());

        when(projetsRepository.save(any(Projets.class))).thenReturn(savedProject);

        ProjetsDTO result = projetsService.createProject(projetsDTO);

        assertNotNull(result);
        assertEquals("Test Project", result.getNom());
        assertEquals("Test Description", result.getDescription());
        assertEquals(1000.0, result.getBudget());
        verify(projetsRepository).save(any(Projets.class));
    }

    @Test
    void getAllProjects_ShouldReturnListOfProjects() {
        Projets project1 = new Projets();
        project1.setId(1L);
        project1.setNom("Project 1");

        Projets project2 = new Projets();
        project2.setId(2L);
        project2.setNom("Project 2");

        when(projetsRepository.findAll()).thenReturn(Arrays.asList(project1, project2));

        List<ProjetsDTO> result = projetsService.getAllProjects();

        assertEquals(2, result.size());
        assertEquals("Project 1", result.get(0).getNom());
        assertEquals("Project 2", result.get(1).getNom());
        verify(projetsRepository).findAll();
    }

    @Test
    void updateProject_ShouldReturnUpdatedProjectDTO() {
        ProjetsDTO projetsDTO = new ProjetsDTO();
        projetsDTO.setNom("Updated Project");
        projetsDTO.setDescription("Updated Description");
        projetsDTO.setBudget(2000.0);

        Projets existingProject = new Projets();
        existingProject.setId(1L);
        existingProject.setNom("Old Project");
        existingProject.setDescription("Old Description");
        existingProject.setBudget(1000.0);

        when(projetsRepository.findById(1L)).thenReturn(Optional.of(existingProject));
        when(projetsRepository.save(any(Projets.class))).thenReturn(existingProject);

        ProjetsDTO result = projetsService.updateProject(1L, projetsDTO);

        assertNotNull(result);
        assertEquals("Updated Project", result.getNom());
        assertEquals("Updated Description", result.getDescription());
        assertEquals(2000.0, result.getBudget());
        verify(projetsRepository).save(existingProject);
    }

    @Test
    void deleteProjectTache_ShouldDeleteProjectAndTaches() {
        Long projectId = 1L;

        doNothing().when(tachesInterface).deleteTachesByProjetId(projectId);
        doNothing().when(projetsRepository).deleteById(projectId);

        assertDoesNotThrow(() -> projetsService.deleteProjectTache(projectId));

        verify(tachesInterface).deleteTachesByProjetId(projectId);
        verify(projetsRepository).deleteById(projectId);
    }

    @Test
    void getProjetById_ShouldReturnProjectDTO() {
        Projets project = new Projets();
        project.setId(1L);
        project.setNom("Project 1");

        when(projetsRepository.findById(1L)).thenReturn(Optional.of(project));

        ProjetsDTO result = projetsService.getProjetById(1L);

        assertNotNull(result);
        assertEquals("Project 1", result.getNom());
        verify(projetsRepository).findById(1L);
    }

    @Test
    void deleteProjet_ShouldDeleteProject() {
        Long projectId = 1L;

        doNothing().when(projetsRepository).deleteById(projectId);

        assertDoesNotThrow(() -> projetsService.deleteProjet(projectId));

        verify(projetsRepository).deleteById(projectId);
    }





}