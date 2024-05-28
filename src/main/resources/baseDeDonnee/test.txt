-- Insérer des exemples dans la table SauvegardePartie
INSERT INTO SauvegardePartie (idPartieNom, nomPartie) VALUES
(1, 'Partie1'),
(2, 'Partie2'),
(3, 'Partie3');

-- Insérer des exemples dans la table ProfEmbauches
INSERT INTO ProfEmbauches (idprof, salaire, nbheure, idPartieNom) VALUES
(1, 2000, 40, 1),
(2, 2500, 35, 2),
(3, 1800, 45, 1);

-- Insérer des exemples dans la table EvenementEnCours
INSERT INTO EvenementEnCours (idEvenement, jourDebut, idPartieNom) VALUES
(1, '2024-06-01', 1),
(2, '2024-06-15', 2),
(3, '2024-07-01', 1);

-- Insérer des exemples dans la table Partie
INSERT INTO Partie (id, nomPartie, estPerdue, nbJours, nbEleves, argent, bonheur, pedagogie, idQualiteRepasCrous, prixVenteRepascrous, idPartieNom) VALUES
(1, 'Partie1', 0, 30, 50, 10000.00, 80, 70, 1, 4.50, 1),
(2, 'Partie2', 1, 20, 40, 8000.00, 70, 60, 2, 4.20, 2),
(3, 'Partie3', 0, 25, 45, 9500.00, 75, 65, 3, 4.80, 3);
