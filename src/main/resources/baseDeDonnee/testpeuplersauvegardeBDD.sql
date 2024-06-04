-- Insertion dans la table Partie
INSERT INTO Partie (idPartie, nomPartie, estPerdue, dateEnCours, nbEleves, argent, bonheur, pedagogie, idQualiteRepasCrous, prixVenteRepascrous) VALUES
(1, 'Nom très long de la partie', 0, '2024-09-30', 50, 10000.0, 80.0, 70.0, 1, 4.5),
(2, 'Partie2', 1, '2024-10-25', 45, 8000.0, 60.0, 65.0, 2, 5.0),
(3, 'Partie3', 0, '2024-12-30', 60, 12000.0, 90.0, 75.0, 3, 6.0);

-- Insertion dans la table ProfEmbauches
INSERT INTO ProfEmbauches (idprof, salaire, nbheure, idPartie) VALUES
(1, 2000, 40, 1),
(2, 2200, 35, 1),
(3, 1800, 30, 2),
(4, 2500, 45, 3),
(5, 2100, 38, 3);

-- Insertion dans la table EvenementEnCours
INSERT INTO EvenementEnCours (idEvenement, jourDebut, idPartie) VALUES
(1, '2024-06-01', 1),
(2, '2024-07-01', 1),
(3, '2024-08-01', 2),
(4, '2024-09-01', 3),
(5, '2024-10-01', 3);
