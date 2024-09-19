package com.Ressources.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RessourceDto {

    private Long id;
    private String nom;
    private String typee;
    private float quantite;
    private Long idTache;
}
