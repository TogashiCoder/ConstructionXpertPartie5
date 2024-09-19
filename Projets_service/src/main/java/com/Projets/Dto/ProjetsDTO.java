package com.Projets.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetsDTO {
    private Long id;
    private String nom;
    private String description;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private Double budget;
}
