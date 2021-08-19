# ride4ever-WS

## Sommaire

## Prérequis
- Java JDK >= 14
- Apache Maven
- Python >= 3.8

## Mise à jour

Compiler le projet avec la commande :
- mvn clean install

Si la commande est exécutée dans VS Code et qu'elle échoue :
- Ouvrir un terminal à partir de la racine du projet
- Fermer VS Code
- Réexécuter la commande dans le nouveau terminal.

Aller dans le répertoire target qui se situe à la racine du projet.

Copier le fichier ride4ever-ws-<VERSION>-jar-with-dependencies.jar
Aller dans le répertoire releases qui se situe à la racine du projet.
Coller le fichier dans ce répertoire 
Supprimer le fichier ride4ever-ws-LATEST.jar
Renommer le fichier ride4ever-ws-<VERSION>-jar-with-dependencies.jar en ride4ever-ws-LATEST.jar.

Copier / coller le fichier ride4ever-ws-LATEST.jar sur une clé USB.
Brancher la clé USB sur le PO connecté au serveur ride4ever.

Aller dans le répertoire /run/media/centos/<USB>
Faire les commandes :
- scp ride4ever-ws-LATEST.jar 192.168.240.1:~/
- ssh 192.168.240.1
- su root
- systemctl stop ride4ever.service
- mv ride4ever-ws-LATEST.jar /opt/ineo
- systemctl start ride4ever.service

