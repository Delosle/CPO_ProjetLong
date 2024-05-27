-- Insérer des données dans la table ProfEmbauches
INSERT INTO ProfEmbauches (idprof, salaire, nbheure) VALUES (1, 5000, 20);
INSERT INTO ProfEmbauches (idprof, salaire, nbheure) VALUES (2, 5500, 22);
INSERT INTO ProfEmbauches (idprof, salaire, nbheure) VALUES (3, 6000, 18);

-- Insérer des données dans la table EvenementEnCours
INSERT INTO EvenementEnCours (idEvenement, jourDebut) VALUES (1, '2024-01-01');
INSERT INTO EvenementEnCours (idEvenement, jourDebut) VALUES (2, '2024-02-01');
INSERT INTO EvenementEnCours (idEvenement, jourDebut) VALUES (3, '2024-03-01');

-- Insérer des données dans la table Partie
INSERT INTO Partie (id, nomPartie, estPerdue, nbJours, nbEleves, argent, bonheur, pedagogie, idQualiteRepasCrous, prixVenteRepascrous)
VALUES (1, 'Partie1', 0, 30, 200, 1500.50, 80, 75, 1, 3.50);
INSERT INTO Partie (id, nomPartie, estPerdue, nbJours, nbEleves, argent, bonheur, pedagogie, idQualiteRepasCrous, prixVenteRepascrous)
VALUES (2, 'Partie2', 1, 25, 150, 1200.75, 70, 65, 2, 4.00);
INSERT INTO Partie (id, nomPartie, estPerdue, nbJours, nbEleves, argent, bonheur, pedagogie, idQualiteRepasCrous, prixVenteRepascrous)
VALUES (3, 'Partie3', 0, 40, 180, 2000.00, 85, 80, 3, 3.75);

-- Insérer des données dans la table SauvegardePartie
INSERT INTO SauvegardePartie (idPartieNom, nomPartie) VALUES (1, 'Sauvegarde1');
INSERT INTO SauvegardePartie (idPartieNom, nomPartie) VALUES (2, 'Sauvegarde2');
INSERT INTO SauvegardePartie (idPartieNom, nomPartie) VALUES (3, 'Sauvegarde3');
