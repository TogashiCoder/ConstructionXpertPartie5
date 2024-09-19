create  table Projets(

                         id SERIAL PRIMARY KEY,
                         nom VARCHAR(255) NOT NULL,
                         description TEXT,
                         date_debut DATE,
                         date_fin DATE,
                         budget DECIMAL(10, 2)
);