-- Crée une table pour les changements de salaire des professeurs
CREATE TABLE ProfEmbauches (
    idprof INTEGER PRIMARY KEY,
    salaire INTEGER,
    nbheure INTEGER
);

-- Crée une table pour les événements en cours
CREATE TABLE EvenementEnCours (
    idEvenement INTEGER PRIMARY KEY,
    jourDebut DATE
);

-- Crée une table pour les parties
CREATE TABLE Partie (
    id INTEGER PRIMARY KEY,
    nomPartie TEXT,
    estPerdue BOOLEAN,
    nbJours INT,
    nbEleves INT,
    argent FLOAT,
    bonheur INT,
    pedagogie INT,
    idQualiteRepasCrous INT,
    prixVenteRepascrous FLOAT
);
