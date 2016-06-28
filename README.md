Pour compiler le projet en ligne de commande :
1. mvn package

Pour supprimer les artéfacts :
1. mvn clean

Les fichiers d'entrée sont à placer dans le répertoire src/main/ressources

Pour lancer le projet en ligne de commande :

1. Se placer à la racine du projet 
2.  Pour lancer le projet avec le fichier par défaut
		==> java -cp target/MowItNow-1.0-SNAPSHOT.jar entryPoint.MowItNow
 
	Pour lancer le projet avec un autre fichier
		==> java -cp target/MowItNow-1.0-SNAPSHOT.jar entryPoint.MowItNow src/main/ressources/autreFichier