![Logo TriSLN Aquaneutron](./src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/img/TriSLN_AquaNeutron.png)

# Projet TriSLN - Aquaneutron

## Équipe de projet
**Chef de projet**: Alexandre GUIHARD

**Développeurs**: Romain VOIVENEL, Clément RENAUDIN, Mohamed-Amine YAHYAOUI, Antoine DELAHAYE

## Technologies utilisés
**IDE**: VS Code, Intellij IDEA

**Langages**: Java, SQL

**Outils**: Git, Draw.io, Discord, Scenebuilder

## Installation

### Java
Pour utiliser l'application, il est nécessaire d'avoir une version de Java d'installée sur votre machine, pour cela, il suffit d'exécuter les commandes:
>1) ***"sudo apt update"***
>2) ***"sudo apt install openjdk-21-jdk"***

Il sera nécessaire d'entrer votre mot de passe de votre machine. De plus, vous devez d'installer Maven permettant de lancer l'application avec la commande:
>***"sudo apt-get -y install maven"***

### MySQL
L'application utilise une base de données MySQL, vous devez donc installer sur votre machine un serveur sur lequel il y aura toutes les données. De plus, vous utilisez un driver se trouvant dans un des dossiers de l'application, cependant, il est nécessaire de l'importer localement avec la commande: 
>***"mvn install:install-file -Dfile=/lib/mariadb-java-client-3.5.2.jar -DgroupId=org.mariadb.jdbc -DartifactId=mariadb-java-client-3.5.2 -Dversion=3.5.2 -Dpackaging=jar -DgeneratePom=true"***

## Lancement de l'application
Pour lancer l'application, il faut exécuter la commande suivante dans un terminal dans le répertoire courant: 
>***"mvn clean compile javafx:run"***
