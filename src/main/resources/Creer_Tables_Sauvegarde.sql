CREATE TABLE ProfEmbauches (
    idprof INTEGER,
    salaire INTEGER NOT NULL,
    nbheure INTEGER NOT NULL,
    idPartie INTEGER NOT NULL  REFERENCES Partie(idPartie),
    PRIMARY KEY (idprof, idPartie)
);

CREATE TABLE EvenementEnCours (
    idEvenement INTEGER PRIMARY KEY,
    jourDebut DATE NOT NULL,
    idPartie INTEGER NOT NULL REFERENCES Partie(idPartie)
);

CREATE TABLE Partie (
    idPartie INTEGER PRIMARY KEY,
    nomPartie TEXT NOT NULL,
    estPerdue BOOLEAN NOT NULL,
    dateEnCours DATE NOT NULL,
    nbEleves INT NOT NULL,
    argent FLOAT NOT NULL,
    bonheur FLOAT NOT NULL,
    pedagogie FLOAT NOT NULL,
    idQualiteRepasCrous INT NOT NULL,
    prixVenteRepascrous FLOAT NOT NULL
);

CREATE TABLE DateEvenementRegulier (
   idEvenementRegulier INTEGER,
   dateEvenement DATE NOT NULL,
   idPartie INTEGER NOT NULL REFERENCES Partie(idPartie),
   PRIMARY KEY (idEvenementRegulier, idPartie)
);

CREATE TABLE ConsommableEnCours (
    idConsommable INTEGER,
    prix DOUBLE,
    idPartie INTEGER NOT NULL REFERENCES Partie(idPartie),
    PRIMARY KEY (idConsommable, idPartie)
);
