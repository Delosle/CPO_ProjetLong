CREATE TABLE ProfEmbauches (
    idprof INTEGER PRIMARY KEY,
    salaire INTEGER NOT NULL,
    nbheure INTEGER NOT NULL,
    idPartie INTEGER NOT NULL  REFERENCES Partie(idPartie)
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
    nbJours INT NOT NULL,
    nbEleves INT NOT NULL,
    argent FLOAT NOT NULL,
    bonheur INT NOT NULL,
    pedagogie INT NOT NULL,
    idQualiteRepasCrous INT NOT NULL,
    prixVenteRepascrous FLOAT NOT NULL
);
