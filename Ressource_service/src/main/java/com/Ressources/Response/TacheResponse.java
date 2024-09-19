package com.Ressources.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TacheResponse {

    private Long id;
    private String nom;
    private String date_debut;
    private String date_fin;
    private String description;
    private String statu;
    private Long idProjet;
}
