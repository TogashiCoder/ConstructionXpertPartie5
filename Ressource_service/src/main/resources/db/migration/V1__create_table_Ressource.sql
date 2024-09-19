create  table Ressource(

                         id SERIAL PRIMARY KEY,
                         nom VARCHAR(255) NOT NULL,
                         typee VARCHAR(200) NOT NULL ,
                         quantite FLOAT,
                         idTache BIGINT
);