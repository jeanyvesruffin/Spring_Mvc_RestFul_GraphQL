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
* h2database


### Driver H2

Telechargement du driver [H2](http://www.h2database.com/html/main.html) - Driver H2


## Spring Boot Application Annotation

**@SpringBootConfiguration**

*Identifie la classe comme source de définitions de bean pour le contexte d'application.*


**@EnableAutoConfiguration**

*Indique à Spring Boot de commencer à ajouter des beans en fonction des paramètres de chemin de classe, d'autres beans et de divers paramètres de propriété. Par exemple, si se spring-webmvctrouve sur le chemin de classe, cette annotation marque l'application en tant qu'application Web et active les comportements clés, tels que la configuration de a DispatcherServlet.*

**@ComponentScan**

*Indique à Spring de rechercher d'autres composants, configurations et services dans le com/examplepackage, le laissant trouver les contrôleurs.*


## Spring Boot Properties

Configurer votre application dans le fichier application.properties à l'aide de la [documentation Spring](https://docs.spring.io/spring-boot/docs/2.0.4.RELEASE/reference/html/common-application-properties.html) - common application properties

Exemple:

	logging.level.org.springframework:debug


## Spring Boot Profiles

Configurer la gestion de vos environnements de developpement, recette, production dans le fichier de configuration application.properties,

Exemple:

	spring.profiles.active=devv
	application-{profile}.properties
	applications-dev.properties
	applications-test.properties
	applications-prod.properties


## Accès à la base de donnée H2 (persistance des données)  avec Spring Boot

**ATTENTION H2 N'EST PAS RECOMMANDE POUR LA PRODUCTION**

Configurer votre database dans le fichier application properties telquel:

	spring.h2.console.enabled=true
	spring.h2.console.path=/h2
	spring.datasource.url=jdbc:h2:mem:bugtracker
	
	
Veuillez saisir les parametres demandé lors de la connexion à la console H2:

http://localhost:8080/h2


## ORM avec JPA

Spring integre Hibernate a travers Spring Data JPA

Methodologie:

1. Création de Entity (Pojo)
2. Création des Repository (Interface qui extends CrudRepository)
3. Création d'une simple query pour tester H2

Exemple:

	// Simple query to populate DB
	@Bean
	public CommandLineRunner demo(IApplicationRepository repository) {
		return (args) -> {
			repository.save(new Application("TrackZilla", "Jean-Yves.Ruffin", "Application for tracking bugs."));
			repository.save(new Application("Expenses", "Mary.Jones","Application to track expense reports."));
			repository.save(new Application("Notification", "Karen.Kane", "Application to send alerts and notifications."));
			for(Application application: repository.findAll()) {
				log.info("The application is: "+ application.toString());
			}
		};
	}


#### Entities

Attention l'entité doit respecter les regles suivante:

1. Elle doit posséder un constructeur vide, public ou protected. Rappelons que ce constructeur vide existe par défaut si aucun constructeur n'existe dans la classe. Dans le cas contraire, il doit être ajouté explicitement.

2. Elle ne doit pas être final, et aucune de ses méthodes ne peut être final.

3. Une entité JPA ne peut pas être une interface ou une énumération.

4. Une entité JPA peut être une classe concrête ou abstraite.


Exemple **Entity** representé par un pojo avec les annotations nécessaire à la construction de l'ORM:

	import javax.persistence.*;
	@Entity
	public class Application {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="application_id")
		private Integer id;
		
		@Column(name = "app_name", nullable = false)
		private String name;
		
		@Column(length = 2000)
		private String description;
		private String owner;
		
	   public Application() {
	   }
	   public Application(String name, String owner, String description) {
		   this.name = name;
		   this.owner = owner;
		   this.description = description;
	   }
    	***GETERS ET SETTERS***
	}

Exemple cardinalité **ManyToOne**

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne
    @JoinTable(name ="ticket_release", joinColumns = @JoinColumn(name = "ticket_fk"), inverseJoinColumns = @JoinColumn(name = "release_fk"))
    private Release release;






### Bug fixes 

#### Maven dependency

Toujours se méfier des **SCOPE** des dependences copier/ coller du site maven repository.

Exemple: H2 par defaut est à test le changer en runtime


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