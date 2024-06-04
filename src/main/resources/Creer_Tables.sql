-- Crée une table pour les professeurs 
CREATE TABLE prof (
    id_prof INTEGER PRIMARY KEY,
    nom TEXT,
    prenom TEXT,
    niveau INTEGER,
    taux_horaire_min INTEGER,
    id_matiere INTEGER,
    description TEXT,
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
    titre TEXT,
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
    impactBonheur INTEGER,
    impactArgent INTEGER,
    impactPedagogie INTEGER,
    titre TEXT,
    frequence FLOAT,
    bonus BOOLEAN
);

-- Crée une table pour les repas au CROUS
CREATE TABLE RepasCrous (
    id_repas INTEGER PRIMARY KEY,
    qualite INTEGER,
    prix FLOAT
);

-- Crée un table pour les repas au Foy
CREATE TABLE RepasFoy (
    id_repas INTEGER PRIMARY KEY,
    nom VARCHAR(60),
    prix FLOAT NOT NULL,
    prixLimite FLOAT NOT NULL,
    image VARCHAR(60)
);

-- Crée une table pour les valeurs de début de partie
CREATE TABLE ValDebPartie (
    prof INTEGER,
    nbEleve INTEGER,
    argent INTEGER,
    bonheur INTEGER,
    pedagogie INTEGER,
    dateDeb DATE
);




-- Insère des données dans la table evenement_regulier
INSERT INTO evenement_regulier (id_eve_reg, description, impactBonheurPos, impactArgentPos, impactPedagogiePos, Titre, periode, debut, impactBonheurNeg, impactArgentNeg, impactPedagogieNeg)
VALUES 
(1, 'Les étudiants ont la possibilité d assister à des séminaires et conférences pour élargir leurs connaissances, découvrir de nouveaux domaines d intérêt et interagir avec des experts. Les sujets abordés peuvent inclure des avancées technologiques, des présentations de recherche et des questions sociales. Participer à ces événements offre aux étudiants l opportunité d améliorer leurs compétences en réseautage, de développer leur compréhension des sujets présentés et de potentiellement découvrir de nouvelles passions et opportunités de carrière.', 10, 0, 15, 'Séminaires et Conférences', 30, '2024-06-01', 0, 0, -10),
(2, 'L école organise un événement mettant en vedette les clubs et associations étudiants. Les étudiants découvrent la diversité des activités extracurriculaires sur le campus, avec chaque club présentant ses activités et ses objectifs. C est une opportunité pour les étudiants d explorer de nouvelles passions, de trouver des communautés d intérêt et de s impliquer dans la vie étudiante, renforçant ainsi leurs réseaux sociaux et leurs compétences en leadership.', 20, 0, 0, 'Show des Clubs', 60, '2024-06-01', -15, 0, 0),
(3, 'Inscrire de nouveaux élèves pour augmenter la jauge d’argent.', 0, 50, 0, 'Inscription Nouveaux Élèves', 90, '2024-06-01', 0, 0, 0),
(4, 'L université organise une rencontre avec des représentants d entreprises locales et nationales. Les étudiants auront l opportunité de rencontrer des professionnels de différents secteurs, d en apprendre davantage sur les opportunités de stages, d emplois à temps partiel et de carrière. Cet événement offre aux étudiants la possibilité de développer leurs réseaux professionnels, de découvrir les exigences du marché du travail et d explorer des chemins de carrière potentiels. Participer à cette rencontre peut aider les étudiants à établir des contacts précieux et à prendre des décisions éclairées concernant leur avenir professionnel.', 15, -10, 0, 'Rencontres Entreprises', 120, '2024-06-01', -5, 0, -5),
(5, 'Organiser des compétitions sportives pour promouvoir le bien-être et le bonheur des étudiants. Ces événements incluent divers sports tels que le football, le basket-ball, le volley-ball, et bien d autres, permettant aux étudiants de participer et de s amuser tout en restant actifs. En plus de favoriser une vie saine, ces compétitions renforcent l esprit de camaraderie et de communauté sur le campus. Les étudiants peuvent ainsi se détendre, se divertir et créer des souvenirs inoubliables avec leurs camarades.', 25, -10, 0, 'Compétitions Sportives', 180, '2024-06-01', -15, 0, 10),
(6, 'Organisee une journée de nettoyage des locaux pour rendre le campus plus propre et agréable. Les étudiants, le personnel et les enseignants sont invités à participer ensemble à cette initiative. En plus d améliorer l environnement de vie et d étude, cet événement renforce la communauté et la fierté collective. Un campus propre contribue au bien-être général et au bonheur de tous.', 10, -10, 10, 'Nettoyage des Locaux', 30, '2024-06-01', -10, 5, -5),
(7, 'Les étudiants doivent décider s ils veulent participer aux campagnes des listes, tradition importante des écoles d ingénieurs. Ces campagnes incluent des activités et des événements visant à promouvoir les listes candidates pour les bureaux des étudiants. Participer à ces campagnes peut renforcer la cohésion de groupe, améliorer le bonheur des élèves et dynamiser la vie étudiante sur le campus. C est une occasion de s engager, de s amuser et de contribuer à l animation de la communauté universitaire.', 5, 0, 0, 'Campagnes des Listes', 365, '2024-06-01', -5, 0, 0),
(8, 'Choisir si je fais un repas de Noël pour modifier le bonheur des élèves et du personnel.', 20, -5, -10, 'Repas de Noël', 365, '2024-12-01', -15, 0, 10),
(9, 'L université propose des après-midi sportifs pour améliorer le bien-être des étudiants. En acceptant, les étudiants peuvent participer à divers sports comme le football, le basketball, le yoga et le jogging. Ces activités aident à rester actifs, à réduire le stress et à socialiser dans un cadre détendu..', 20, 0, 0, 'Après-midi Sportifs', 30, '2024-06-01', -10, 0, 5),
(10, 'L université propose aux étudiants de payer un repas pour le personnel. En acceptant, l étudiant montre sa reconnaissance envers le personnel, ce qui peut améliorer le moral et renforcer les relations sur le campus. Cependant, cette décision impactera leur budget personnel, les mettant ainsi en difficulté financière. Refuser signifie conserver ses ressources financières, mais ne pas profiter de cette opportunité pour montrer sa gratitude.', 0, -20, 0, 'Repas pour le Personnel', 90, '2024-06-01', -10, 5, 0);

-- Insère des données dans la table evenement_irregulier
INSERT INTO evenement_irregulier (id_eve_irre, description, impactBonheur, impactArgent, impactPedagogie, Titre, frequence, bonus)
VALUES 
(1, 'Les enseignants sont en grève pour protester contre l absentéisme rampant et la pénurie chronique de tampons pour effacer les tableaux à craie. Fatigués de jongler entre classes désertes et tableaux illisibles, ils réclament des soutions pour redonner vie à leurs salles de classe!.', -10, 0, -10, 'Grèves des Enseignants', 0.2, FALSE),
(2, 'Nous regrettons de vous informer que notre livraison de chocolatines est retardée pour une raison aussi insolite qu inattendue : notre camion a été attaqué par une bande de singes farceurs en chemin vers Chocolatville !

L annulation de la livraison des chocolatines au foyer de l école a plongé les étudiants dans une tristesse collective. Privés de leur douce pause gourmande, l humeur générale a chuté, transformant les sourires en soupirs. Les chocolatines étant la clé du bonheur matinal, l impact se fait ressentir dans tout l établissement.', -10, -50, 0, 'Annulation Livraison Chocolatine', 0.2, FALSE),
(3, 'En cette période où le stress des études peut parfois sembler pesant, nous avons une délicieuse surprise pour vous ! Pour vous remercier de votre engagement et de votre fidélité, nous avons le plaisir d annoncer une journée spéciale de livraison gratuite de chocolatines, rien que pour vous !', 10, 50, 0, 'Livraison Gratuite Chocolatine', 0.25, TRUE),
(4, 'Chers membres de la communauté,

Nous vous informons avec une pointe d humour mais aussi de sérieux qu une invasion de canards a été repérée à la Rivière des MFEE ! Ces adorables mais envahissants palmipèdes semblent être à la recherche d un point d eau paisible où se rafraîchir, et notre rivière semble être leur nouvelle destination favorite.', 15, 0, 0, 'ALERTE : Invasion de Canards à la Rivière des MFEE !d', 0.15, TRUE),
(5, 'Manque de chance : vous avez engagé un professeur incompétent. 

Il arrive 30 minutes après le début du cours et ne donne qu un seul exercice à faire pendant toute l heure. Une fois que les étudiants l ont terminé, il se cache derrière son ordinateur.', 0, 0, -10, 'Mauvais Professeurs', 0.2, FALSE),
(6, 'Chanceux d avoir engagé un excellent professeur ! Il arrive à l heure, donne des explications claires et variées, et engage les étudiants dans des activités enrichissantes tout au long du cours. Il est attentif aux besoins individuels des élèves et reste disponible pour les aider après la classe. En résumé, il incarne l excellence pédagogique', 0, 0, 10, 'Bons Professeurs', 0.3, TRUE),
(7, 'Une invasion de punaises a plongé l école dans le chaos et l inconfort. Les salles de classe et les couloirs sont infestés, perturbant les activités quotidiennes. Les élèves et le personnel sont constamment sur leurs gardes, craignant les piqûres et l agitation causée par ces insectes indésirables. Cette situation désagréable nécessite une intervention immédiate pour rétablir un environnement d apprentissage sain et sûr.', -10,0,-10, 'Invasion de punaises', 0.1, FALSE),
(8, 'Venez célébrer avec nous lors d une soirée étudiante sensationnelle avec des invités spéciaux de renom : Patrick Sébastien et Sébastien Patoche ! Préparez-vous à être émerveillés par des performances incroyables, des chansons entraînantes et des sketchs hilarants qui vous feront rire aux éclats toute la nuit.

Mais ce n est pas tout ! Nous réservons également une surprise spéciale qui ajoutera une touche magique à cette soirée déjà inoubliable. Attendez-vous à des moments de pur plaisir et d excitation alors que nous vous offrons une expérience unique que vous n êtes pas prêts d oublier.', 20, 0, 0, 'Soirées Étudiantes', 0.3, TRUE),
(9, 'Lors du lancement d une simulation par l un de nos étudiants, un ordinateur a malheureusement pris feu. Nous sommes reconnaissants que l incident ait été rapidement maîtrisé et que seul l ordinateur ait été endommagé.', -10, 0, 0, 'Dysfonctions Équipements', 0.15, FALSE),
(10, 'Nous vous informons qu un incident malheureux s est produit récemment impliquant une chaise défectueuse, ayant entraîné une légère blessure pour l un de nos élèves. Nous tenons à présenter nos excuses les plus sincères pour cet incident et à assurer à tous notre engagement envers la sécurité et le bien-être de nos étudiants.

En signe de notre regret pour cet incident et pour compenser l inconfort causé, nous nous engageons à remplacer la chaise défectueuse par une nouvelle et sûre. De plus, nous offrirons à l élève concerné un Mars en guise de geste d excuse pour cet incident regrettable. ', -10, 0, 0, 'Dysfonctions Équipements', 0.15, FALSE);

-- Insère des données dans la table RepasCrous
INSERT INTO RepasCrous (id_repas, qualite, prix) VALUES
(1, 1, 1.10),
(2, 2, 1.30),
(3, 3, 1.70),
(4, 4, 2.00);

-- Insère des données dans la table RepasFoy
INSERT  INTO RepasFoy (id_repas, nom, prix, prixLimite, image) VALUES
(1, 'Café', 0.95, 1.50, 'cafe.jpg'),
(2, 'Chocolatine', 1.30, 2.00, 'chocolatine.jpg'),
(3, 'Chocolat chaud', 0.8, 1.50, 'choco_chaud.jpeg'),
(4, 'Kinder Bueno', 1.5, 2.00, 'kinder.jpeg'),
(5, 'Coca', 1.20, 2.50, 'coca.jpg'),
(6, 'Croissant', 0.70, 1.3, 'croissant.jpg'),
(7, 'Jus de fruit', 0.5, 1, 'jus_fruits.jpeg');

-- Insère des données dans la table ValDebPartie
INSERT INTO ValDebPartie (prof, NbEleve, Argent, Bonheur, Pedagogie, dateDeb) 
VALUES (0, 10, 1500, 50, 50, '2024-09-01');


-- Insérer des données dans la table matiere
INSERT INTO matiere (id_matiere, nom, bonheur, pedagogie) VALUES (1, 'Informatique', 8, 9);
INSERT INTO matiere (id_matiere, nom, bonheur, pedagogie) VALUES (2, 'Anglais', 7, 6);
INSERT INTO matiere (id_matiere, nom, bonheur, pedagogie) VALUES (3, 'Réseau', 9, 7);
INSERT INTO matiere (id_matiere, nom, bonheur, pedagogie) VALUES (4, 'Communication', 5, 5);

-- Insérer des données dans la table prof
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_min, id_matiere, description) VALUES (1, 'Dupont', 'Jean', 80, 30, 1, 'Jean Dupont est ingénieur depuis vingt-cinq ans et souhaiterait intervenir à mi-temps dans votre école. Son manque d''expérience dans l''enseignement est largement compensé par une grande connaissance de son domaine d''expertise. De plus, les étudiants apprécient l''approche de son sujet, concrète et reliée au monde du travail.');
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_min, id_matiere, description) VALUES (2, 'Martin', 'Sophie', 60, 28, 4, 'Le professeur semble très motivé à rejoindre votre équipe. A l''issue de votre entretien, vous avez immédiatement décidé de mettre à jour votre profil LinkedIn pour la première fois en 10 ans et créé votre portfolio. Ses capacités en leadership ne font aucun doute.');
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_min, id_matiere, description) VALUES (3, 'Bernard', 'Pierre', 50, 25, 3, 'Pierre Bernard vient tout de juste de démarrer sa thèse en mathématiques dans votre école. S''il ne fait aucun doute qu''il est passionné et compétent dans son domaine, il semble encore manquer d''assurance et de clarté dans ses explications.');
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_min, id_matiere, description) VALUES (4, 'Petit', 'Marie', 100, 22, 3, 'Après plusieurs décennies passées dans le monde de la recherche, à cotoyer les meilleurs de son domaine et à publier ses articles dans des revues de prestige, Marie Petit a décidé de finir sa carrière en revenant à son premier amour, l''enseignement. Lors de votre entretien, vous avez été bluffé par son énergie communicative et sa capacité à expliquer si clairement des sujets pourtant complexes.');
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_min, id_matiere, description) VALUES (5, 'Durand', 'Luc', 30, 20, 4, 'Luc Durand a de solides références et semble maîtriser son domaine. Toutefois, le fait qu''il en soit à sa septième école en l''espace de cinq ans est étrange. D''après ses anciens employeurs, ses méthodes pédagogiques particulières seraient à l''origine de ces changements à répétition.');
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_min, id_matiere, description) VALUES (6, 'Leroy', 'Christophe', 70, 29, 1, 'Christophe Leroy a passé dix ans à travailler en tant que consultant indépendant avant de se tourner vers l''enseignement. Ses compétences techniques sont indéniables, mais il a du mal à s''adapter aux structures académiques. Véritable électron libre, il lui arrive souvent de se perdre dans des détails techniques. Toutefois, il est à l''écoute des retours des étudiants et cherche constamment à améliorer sa façon d''enseigner. ');
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_min, id_matiere, description) VALUES (7, 'Moreau', 'Emma', 90, 30, 1, 'Avec une carrière impressionnante dans des entreprises de la Silicon Valley, Emma Moreau apporte une expertise de pointe en technologie de l''information. Cependant, son style de communication direct et parfois abrupt peut déstabiliser les étudiants plus sensibles. Malgré cela, ceux qui suivent ses cours jusqu''au bout ressortent avec des compétences très recherchées sur le marché du travail.');
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_min, id_matiere, description) VALUES (8, 'Simon', 'Chloe', 30, 24, 2, 'Chloe Simon, après avoir enchaîné des postes temporaires dans plusieurs universités, cherche à rejoindre votre école. Bien que passionnée et très au fait des dernières avancées dans son domaine, son manque de préparation et d''organisation se traduit souvent par des cours désordonnés. Sa tendance à improviser sans avoir un plan clair peut être un problème pour les étudiants.');
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_min, id_matiere, description) VALUES (9, 'Michel', 'Lucas', 60, 21, 3, 'Ayant travaillé pendant des années comme chercheur principal dans un laboratoire de renommée mondiale, Lucas Michel a une compréhension approfondie des sujets qu''il enseigne. Cependant, son approche trop théorique et sa difficulté à rendre les sujets accessibles aux débutants sont souvent cités comme des points faibles dans ses évaluations. Sa passion pour la recherche l''amène parfois à négliger ses obligations pédagogiques.');
INSERT INTO prof (id_prof, nom, prenom, niveau, taux_horaire_min, id_matiere, description) VALUES (10, 'Garcia', 'Julie', 50, 19, 4, 'Julie Garcia est un ancienne militaire qui a décidé de se reconvertir dans l''enseignement après une carrière de 20 ans dans les forces armées. Sa discipline rigide et son exigence élevée peuvent être intimidantes pour certains étudiants. Bien que très efficace et rigoureuse, son manque de flexibilité pédagogique peut poser problème, surtout pour ceux qui ont besoin de plus de soutien et d''encouragement.');

