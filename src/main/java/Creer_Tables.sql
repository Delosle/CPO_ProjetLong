-- Crée une table pour les professeurs
CREATE TABLE prof (
    id_prof INTEGER PRIMARY KEY,
    nom TEXT,
    prenom TEXT,
    niveau INTEGER,
    taux_horaire_minute INTEGER,
    id_matiere INTEGER,
    FOREIGN KEY (id_matiere) REFERENCES matiere(id_matiere)
);

-- Crée une table pour les matières
CREATE TABLE matiere (
    id_matiere INTEGER PRIMARY KEY,
    nom TEXT,
    bonheur INTEGER,
    pedagogie INTEGER
);


-- Crée une table pour les événements réguliers
CREATE TABLE evenement_regulier (
    id_eve_reg INTEGER PRIMARY KEY,
    description TEXT,
    impactBonheurPos INTEGER,
    impactArgentPos INTEGER,
    impactPedagogiePos INTEGER,
    Titre TEXT,
    periode INTEGER,
    debut DATE,
    impactBonheurNeg INTEGER,
    impactArgentNeg INTEGER,
    impactPedagogieNeg INTEGER
);

-- Crée une table pour les événements irréguliers
CREATE TABLE evenement_irregulier (
    id_eve_irre INTEGER PRIMARY KEY,
    description TEXT,
    impactBonheurPos INTEGER,
    impactArgentPos INTEGER,
    impactPedagogiePos INTEGER,
    Titre TEXT,
    frequence INTEGER,
    bonus BOOLEAN
);

-- Crée une table pour les repas au CROUS
CREATE TABLE RepasCrous (
    id_repas INTEGER PRIMARY KEY,
    qualite INTEGER,
    prix FLOAT
);

-- Crée une table pour les valeurs de début de partie
CREATE TABLE ValDebPartie (
    prof INTEGER,
    NbEleve INTEGER,
    Argent INTEGER,
    Bonheur INTEGER,
    Pedagogie INTEGER,
    dateDeb DATE
);




-- Insère des données dans la table evenement_regulier
INSERT INTO evenement_regulier (id_eve_reg, description, impactBonheurPos, impactArgentPos, impactPedagogiePos, Titre, periode, debut, impactBonheurNeg, impactArgentNeg, impactPedagogieNeg)
VALUES 
(1, 'Générer des séminaires et conférences pour augmenter le bonheur et le niveau de pédagogie des étudiants.', 10, 0, 15, 'Séminaires et Conférences', 30, '2024-06-01', 0, 0, 0),
(2, 'Organiser le show des clubs pour augmenter le bonheur des étudiants.', 20, 0, 0, 'Show des Clubs', 60, '2024-06-01', 0, 0, 0),
(3, 'Inscrire de nouveaux élèves pour augmenter la jauge d’argent.', 0, 50, 0, 'Inscription Nouveaux Élèves', 90, '2024-06-01', 0, 0, 0),
(4, 'Organiser des rencontres avec des entreprises pour augmenter le bonheur des étudiants.', 15, 0, 0, 'Rencontres Entreprises', 120, '2024-06-01', 0, 0, 0),
(5, 'Organiser des compétitions sportives pour augmenter le bonheur des étudiants.', 25, 0, 0, 'Compétitions Sportives', 180, '2024-06-01', 0, 0, 0),
(6, 'Nettoyer les locaux pour rendre les locaux plus propres et augmenter le bonheur général.', 10, 0, 0, 'Nettoyage des Locaux', 30, '2024-06-01', 0, 0, 0),
(7, 'Choisir si je participe aux campagnes des listes pour modifier le bonheur de mes élèves.', 5, 0, 0, 'Campagnes des Listes', 365, '2024-06-01', -5, 0, 0),
(8, 'Choisir si je fais un repas de Noël pour modifier le bonheur des élèves et du personnel.', 15, 0, 0, 'Repas de Noël', 365, '2024-12-01', 0, 0, 0),
(9, 'Mettre à disposition des après-midi sport pour les élèves pour augmenter leur bien-être (bonheur).', 20, 0, 0, 'Après-midi Sportifs', 30, '2024-06-01', 0, 0, 0),
(10, 'Imposer au joueur de payer un repas pour le personnel pour le challenger et le mettre en difficulté.', 0, -20, 0, 'Repas pour le Personnel', 90, '2024-06-01', 0, 0, 0);

-- Insère des données dans la table evenement_irregulier
INSERT INTO evenement_irregulier (id_eve_irre, description, impactBonheurPos, impactArgentPos, impactPedagogiePos, Titre, frequence, bonus)
VALUES 
(1, 'Imposer des grèves des enseignants pour challenger l’utilisateur et ajouter des difficultés dans le jeu.', -15, 0, 0, 'Grèves des Enseignants', 1, FALSE),
(2, 'Des événements aléatoires arrivent pour rendre plus intéressant le jeu.', 0, 0, 0, 'Événements Aléatoires', 1, FALSE),
(3, 'Annuler la livraison de chocolatine pour faire baisser la jauge de bonheur.', -10, 0, 0, 'Annulation Livraison Chocolatine', 1, FALSE),
(4, 'Créer une livraison gratuite de chocolatine pour faire augmenter le bonheur des élèves.', 10, 0, 0, 'Livraison Gratuite Chocolatine', 1, TRUE),
(5, 'Créer une invasion de canard pour faire augmenter la jauge de bonheur.', 15, 0, 0, 'Invasion de Canard', 1, TRUE),
(6, 'Faire intervenir des mauvais professeurs pour faire baisser la jauge de pédagogie.', 0, 0, -10, 'Mauvais Professeurs', 1, FALSE),
(7, 'Faire intervenir des bons professeurs pour faire augmenter la jauge de pédagogie.', 0, 0, 10, 'Bons Professeurs', 1, TRUE),
(8, 'Créer une invasion de punaise pour challenger le joueur.', 0, 0, 0, 'Invasion de Punaise', 1, FALSE),
(9, 'Créer des soirées pour faire augmenter le bonheur des élèves.', 20, 0, 0, 'Soirées Étudiantes', 1, TRUE),
(10, 'Créer des dysfonctions (équipement qui cassent) pour challenger le joueur.', -10, 0, 0, 'Dysfonctions Équipements', 1, FALSE);


-- Insère des données dans la table RepasCrous
INSERT INTO RepasCrous (id_repas, qualite, prix) VALUES
(1, 1, 1.10),
(2, 2, 1.30),
(3, 3, 1.70),
(4, 4, 2.00);

-- Insère des données dans la table ValDebPartie
INSERT INTO ValDebPartie (prof, NbEleve, Argent, Bonheur, Pedagogie, dateDeb) 
VALUES (0, 10, 1500, 50, 50, '2024-09-01');



-- Insérer des données dans la table matiere
INSERT INTO matiere (id_matiere, nom, bonheur, pedagogie) VALUES (1, 'Mathématiques', 8, 9);
INSERT INTO matiere (id_matiere, nom, bonheur, pedagogie) VALUES (2, 'Physique', 7, 8);
INSERT INTO matiere (id_matiere, nom, bonheur, pedagogie) VALUES (3, 'Chimie', 9, 7);

-- Insérer des données dans la table prof
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_minute, id_matiere) VALUES (1, 'Dupont', 'Jean', 5, 30, 1);
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_minute, id_matiere) VALUES (2, 'Martin', 'Sophie', 4, 28, 2);
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_minute, id_matiere) VALUES (3, 'Bernard', 'Pierre', 3, 25, 3);
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_minute, id_matiere) VALUES (4, 'Petit', 'Marie', 2, 22, 1);
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_minute, id_matiere) VALUES (5, 'Durand', 'Luc', 1, 20, 2);
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_minute, id_matiere) VALUES (6, 'Leroy', 'Emma', 4, 29, 3);
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_minute, id_matiere) VALUES (7, 'Moreau', 'David', 5, 30, 1);
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_minute, id_matiere) VALUES (8, 'Simon', 'Chloe', 3, 24, 2);
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_minute, id_matiere) VALUES (9, 'Michel', 'Lucas', 2, 21, 3);
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_minute, id_matiere) VALUES (10, 'Garcia', 'Julie', 1, 19, 1);

