# CPO_ProjetLong
<<<<<<< HEAD
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Delosle_CPO_ProjetLong&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Delosle_CPO_ProjetLong)
=======

# Telecharger le JDK

Afin d’installer jdk-22 il faut télécharger et dézipper l’archive via le site d’oracle : 	

https://download.oracle.com/java/22/latest/jdk-22_linux-x64_bin.tar.gz (sha256)

Placer votre JDK dézipper à la racine du projet, donc sous le dossier CPO_ProjetLong

# Ajouter votre JDK au sein du projet

Ouvrir eclipse. Faire un clic droit sur le projet puis aller dans ‘build path’ et ‘configurer build path’.

Dans cette fenêtre aller dans Librairies, faire un remove de l’ancien JRE.

Ensuite faire add libraries → JRE system Library → Alternate JRE → installed JREs → Add → Standard VM → next → directory → récupère le dossier → open → Finish → le selectionner et apply and close → le selectionner dans le menu déroulant → Finish → apply and close

Ensuite dans window faire clean, puis clic droit sur le projet → refresh et ensuite vous pouvez run !

# Pour une installation maven correcte : 
Faire un pull 

Pour eclipse :
Sur eclipse, allez dans "File" --> "Import" --> "General" --> "Projects from Folder or Archive"
Selectionner le dossier CPO_ProjetLong 
Allez dans le fichier pom.xml

Sur la première ligne : Allez tout à droite et accepter le download ci nécessaire 

Faire un clic droit sur le projet --> Maven --> Update Project 
Selectionnez "Force update of Snapshots/Releases"
OK 

Votre projet est prêt :)
>>>>>>> eb2fcf142394519aa37b90642e00f83c25536d09
