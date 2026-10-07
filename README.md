# Generateur de QR Code PDF (TP Java)

Bienvenue sur le depot de ce projet Java. Ce programme a ete realise dans le cadre d'un TP d'architecture logicielle divise en deux parties. L'objectif etait de concevoir une application de bureau permettant de generer des QR Codes et de les integrer automatiquement dans des documents PDF.

L'application repose sur une architecture MVC (Modele-Vue-Controleur) afin de separer l'interface visuelle de la logique de generation et de traitement des donnees.

## Fonctionnalites

Le developpement s'est fait en deux etapes principales, d'une version de base vers un outil personnalisable :

### Partie 1 : Les fondations
* **Generation de QR Code :** Transformation d'un texte ou d'un lien en image QR Code fonctionnelle.
* **Creation de PDF :** Integration automatique du QR Code au centre d'un document PDF.
* **Interface Graphique :** Fenetre de saisie simple developpee avec Java Swing.
* **Ergonomie :** Ouverture automatique du document PDF genere avec le lecteur par defaut du systeme.

### Partie 2 : Personnalisation et UX avancee
* **Style personnalise :** L'utilisateur peut modifier la police de caracteres et la couleur du texte integre au PDF[cite: 2].
* **Insertion d'images :** Possibilite d'ajouter une image stockee sur l'ordinateur directement dans le rendu final du PDF[cite: 2].
* **Sauvegarde de profil :** Les preferences de l'utilisateur (police, couleur, image) sont enregistrees et rechargees automatiquement a chaque lancement du projet[cite: 2].
* **Interface dynamique :** Integration d'une barre de progression (JProgressBar) pour indiquer l'etat d'avancement de la generation en arriere-plan[cite: 2].

## Technologies utilisees
* **Java & Swing :** Langage principal et composants pour l'interface graphique.
* **Maven :** Gestionnaire de projet et de dependances (via le fichier pom.xml).
* **ZXing :** Bibliotheque utilisee pour l'encodage et la generation des QR Codes.
* **iText :** API permettant la creation, le formatage et l'insertion d'elements dans les fichiers PDF.
* **JUnit 5 :** Framework utilise pour mettre en place des tests unitaires automatisant la verification du code[cite: 2].

## Comment lancer le projet ?

1. Clonez ce repertoire sur votre machine.
2. Ouvrez le projet dans votre IDE (Eclipse, IntelliJ...) et importez-le en tant que projet Maven.
3. Attendez la fin du telechargement des dependances Maven.
4. Executez le fichier principal : `controleur.Controle.java`.

## Tests Unitaires

Pour s'assurer que la generation des fichiers (PNG, PDF) et la sauvegarde du profil s'effectuent sans erreur[cite: 2], des tests unitaires sont disponibles. Executez la classe `modele.ModeleTest.java` avec JUnit pour lancer les verifications. Ces tests creent des fichiers temporaires, verifient leur validite, puis nettoient l'espace de travail.
