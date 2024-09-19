create  table Taches(

                         id SERIAL PRIMARY KEY,
                         nom VARCHAR(255) NOT NULL,
                         description TEXT,
                         date_debut DATE,
                         date_fin DATE,
                         statu VARCHAR(200) NOT NULL,
                         idProjet BIGINT
);