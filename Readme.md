# SPRING MVC RESTFUL GRAPHQL

Remise à niveau Spring.

## Commencer

Cloner le projet dans Eclipse pour executer et voir les différents TP réalisés

Monter le projet et demarrer votre serveur.

Accessible à l'adresse : http://localhost:8080/BugTracking



### Conditions préalables

Sous Windows:

L'utilisation de chocolotey comme gestionnaire de package a été utilisé.

 [Installer chocolatey](https://chocolatey.org/)

### L'installation

Executer, en mode administrateur, dans votre powerShell, les lignes de commandes suivantes, afin d'installer les packages nécessaires au lancement de votre environnement de développement.

**IDE Eclipse 4.15** 

```
choco install eclipse
```
**OpenJDK 14.0.0 14.0.0** 

```
choco install openjdk14
```
**Maven 3.6.3** 

```
choco install maven
```
**Apache Tomcat 9.0.30** 

```
choco install tomcat
```
**MySQL (Community Server) 8.0.19**

```
choco install mysql
```
**MySQL Connector - Java 8.0.15**

```
choco install mysql-connector-java
```
**MySQL Workbench 8.0.19**

```
choco install mysql.workbench
```
**Node JS 13.12.0**

```
choco install nodejs
```
**Git 2.26.0**

```
choco install git
```
**TcpView 3.05**

```
choco install tcpview
```
**telnet (Install) 0.9.0**

```
choco install telnet
```
**Spring Tool Suite 3.9.6**

```
choco install springtoolsuite
```

**PLUGINS ECLIPSE**

- EGit
- Eclipse Tomcat Plugin 9.1.4
- Java 14 Support for Eclipse 2020-03
- Maven (Java EE) Integration for Eclipse WTP (Luna/Mars) 1.2.0
- Spring Tools 3 (standalone Edition) 3.9.12.RELEASE
- Spring Tools 3 Add-On for Spring Tools 4 3.9.12.RELEASE
- Spring Tools 4 (aka Spring Tool Suite 4) 4.6.0.RELEASE
- ResourceBundle Editor


*En cours d'édition End with an example of getting some data out of the system or using it for a little demo*


### Maven Dependency

* spring-boot-starter-web
* spring-boot-starter-tomcat
* thymeleaf-spring5
* spring-boot-starter-test
* hibernate-validator

### Driver H2

Telechargement du driver [H2](http://www.h2database.com/html/main.html) - Driver H2



## PARAG 1


## PARAG 2

## PARAG 3

## TPARAG 4



### Bug fixes 

#### Server TOMCAT

Erreur 404 lors du démarrage TOMCAT

Cliquer sur propriete > General puis cliquer sur le bouton "Switch Location", celle-ci doit se mettre à jours.

Double-cliquer ensuite sur le server puis cocher dans la rubrique Server Locations sur la case > Use Tomcat installation (takes controle of Tomcat installation)

Votre server TOMCAT est maintenant operationnel à l'adresse URl: localhost:8080

#### ResourcesBundle

**ATTENTION à la signature (orthographe) du bean localeResolver()**, ne pas oublier les "e"

	@Bean
		public LocaleResolver localeResolver() {
			SessionLocaleResolver slr = new SessionLocaleResolver();
			slr.setDefaultLocale(Locale.FRANCE);
			return slr;
		}

#### Java JSP

Deprecié il faut etre en java 1.8 pour que cela fonctionne