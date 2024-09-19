package com.Taches.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetResponse {

    private Long id;
    private String nom;
    private String description;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private Double budget;
}
