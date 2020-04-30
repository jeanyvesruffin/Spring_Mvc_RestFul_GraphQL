# SPRING MVC RESTFUL GRAPHQL

Remise à niveau Spring.

## Commencer

Cloner le projet dans Eclipse pour executer et voir les différents TP réalisés

Monter le projet et demarrer votre serveur.


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


*En cours d'édition End with an example of getting some data out of the system or using it for a little demo*


### Maven Dependency

* spring-boot-starter-web
* spring-boot-starter-tomcat
* thymeleaf-spring5
* spring-boot-starter-test
* h2database
* graphql-spring-boot-starter
* graphql-java-tools


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


## Mise en place du package service

Le package service comporte des interfaces. Ces interfaces sont ensuite implementées avec en leurs seins les attributs membre a la classe du repository associé.
Ces classes implementaient utilises le stereotype @Service

Exemple:

	@Service
	public class ReleaseServiceImpl implements IReleaseService {

		@Autowired
		private IReleaseRepository releaseRepository;
		
		@Override
		public List<Release> listReleases() {
			return (List<Release>) releaseRepository.findAll();
		}
	}


L'interface IReleaseService, qui contient le "contrat" de l'interface est implementé dans la class ReleaseServiceImpl qui défini le contrat de l'interface à l'aide de @Override


## Mise en place du controller

Maintenant que nos services sont impémentés nous pouvons les utiliser dans nos controllers.

Nous annotons avant tout notre classe @Controller.

Exemple:

	@Controller
	public class TzaController

Premièrement, declarer les attributs membres aux interfaces Service.

Exemple:

	  private IApplicationService applicationService;
	
Secondement, on genere les setters, que l'on branche @Autowired.
Cela aura pour effet de pouvoir attribuer une valeur à un model à l'aide de ces méthodes.

	@Autowired
    public void setApplicationService(IApplicationService applicationService) {
        this.applicationService = applicationService;
    }

Enfin, nous declarons les methodes @GetMapping permettant de consulter les models du repository (sur les url corrsepondant ici localhost:8080/application) à l'aide du parametre Model issu de Spring.ui qui à pour effet d'indiquer à Spring que le return sera un model.

	@GetMapping("/applications")
	    public String retrieveApplications(Model model){
	        model.addAttribute("applications", applicationService.listApplications());
	        return "applications";
	    }

## Building a RESTFul Web Application with Spring Boot

Concepts étudié:

- Construction du service RESTFul
- Style de l'architecture REST
- Codes des responses HTTP
- Annotations
- Manipulation des exceptions (Exception Handling)


**Style de l'architecture REST**

	@GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> list = ticketService.listTickets();
        return new ResponseEntity<List<Ticket>>(list, HttpStatus.OK);
    }
    

ResponseEntity definition: Extension of HttpEntity that adds a HttpStatus status code.Used in RestTemplate as well @Controller methods. 
		
## Building a GraphQL Server with Spring Boot
		
Concept étudié:

- Language de requete pour API
- Apercu de GraphQL
- Schema (Defines data points offered via an API, Data types and relationships, operation available and graphql-java-tools parses schemas ending in ".graphqls")
- Server GraphQL
- Resolvers, queries et mutations
- Exception handling
- GraphiQL

GraphQl offre une grande flexibilité à notre API REST, qui est elle très rigide.
		

### Mise en oeuvre graphqls

**ETAPE 1 : Schema**

Créer un fichier .graphqls dans le dossier src/main/resources qui sera notre schema graphqls.

Déclarer dans le schéma .graphqls le type Application, Query et Mutation.


**ETAPE 2 : Query**

Creer un package resolver.
Creer une classe Query qui implemente l'interface GraphQLQueryResolver, dans le package resolver. Ne pas oublier l'annotation @Component de Spring.

Instancier une interface repository, ici IApplicationRepository ==> pour avoir acces à notre CRUD.

Puis, définir les methodes nécessaire au query (ici findAllApplication et countApplication), défini dans le schema.

Exemple ici:

	public List<Application> findAllApplication(){	
		return applicationRepository.findAll();
	}
	public long countApplication() {
		return applicationRepository.count();
	}

**ETAPE 3 : Mutateur**

Creer un package mutator.
Creer une classe Mutator qui implemente l'interface GraphQLMutationResolver, dans le package mutator. Ne pas oublier l'annotation @Component de Spring.

Cela nous sera utile afin de modifier, create, update ou delete (mutation) les valeurs de notre base de données.

Instancier une interface repository, ici IApplicationRepository ==> pour avoir acces à notre CRUD.
Puis, définir les methodes nécessaire au query (ici newApplication, deleteApplication et updateApplicationOwner), défini dans le schema.

Exemple ici:

	public Application newApplication(String name, String owner, String description) {
		Application app = new Application(name, description, owner);
		applicationRepository.save(app);
		return app;
	}
	public boolean deleateApplication(Long id) {
		applicationRepository.deleteById(id);
		return true;
	}
	public Application updateApplication(String owner, Long id) {
		Optional<Application> optionalApplication = applicationRepository.findById(id);
		if(optionalApplication.isPresent()) {
			Application application = optionalApplication.get();
			application.setOwner(owner);
			applicationRepository.save(application);
			return application;
		} else {
			throw new ApplicationNotFoundException("L'application id "+id+" n'est pas trouvé");
		}
	}

**ETAPE 3 : Exception**

Creer un package exception.
Creer une classe Exception (ex: ApplicationNotFoundException.java)  qui implemente l'interface GraphQLError et extends RuntimeException, dans le package exception. **Attention**, cette classe ,'as pas besoin de l'annotation @Component de Spring.


**ETAPE 4 : Tester graphQl**

Démarrer votre projet et consulter l'url:

http://localhost:8080/graphiql

Sur cette page vous trouverez:

- sur le panneau de gauche un editeur de query avec le bouton execute query en haut.

- sur le panneau de droite vous trouverez de la documentation généré automatiquement.
Vous pourrez y consulter vos schemas et mutateur.


Exemple de query:
	
	{
	  findAllApplications{
	    id
	  }
	}
	
Return

	{
	  "data": {
	    "findAllApplications": [
	      {
	        "id": "1"
	      },
	      {
	        "id": "2"
	      },
	      {
	        "id": "3"
	      },
	      {
	        "id": "4"
	      },
	      {
	        "id": "5"
	      }
	    ]
	  }
	}

Reppelons nous que la base de donnée est persistante nous retirons temporairement de fichier data.sql en le renommant data.sqlzzzzzzzzz et clean install restart l'application.

Nous allons remplir la base de donnée à l'aide de l'interface GraphiQl.

Exemple mutation de creation:

	mutation{
	  newApplication(
	    name: "MyApp",
	    owner: "Jean-Yves",
	    description: "Test Mutateur newApplication"
	  ) {
	    id
	  }
	}


Exemple mutation de update (attention l'id doit exister"):


	mutation{
		updateApplicationOwner(
		newOwner: "Brigite", id : 3
		) {
		  id
		}
	}

Exemple test Exception sur l'update d'un id inexistant ex: 999:

	"data": null,
	  "errors": [
	    {
	      "message": "Exception while fetching data (/updateApplicationOwner) : L'application n'as pas été trouvé id : ",
	      "path": [
	        "updateApplicationOwner"
	      ],
	      
	      
## Enabling actuators, metrics, and health indicators

Concept:

- Spring boot actuator: permet de controler et auditer la santé de l'appli. Trace les metrics et http. Expose les endpoints HTTP ou JMX.
- Actuator endpoints
- Custom metric endpoints
- Health indicators
- Monitoring capabilities


**Spring boot actuator**

Ajouter la dependence spring-boot-starter-actuator à votre projet.
Configurer votre fichier application.properties telque:

	management.endpoints.web.exposure.include=info,health,metrics,loggers,beans,mappings
	management.endpoint.health.show-details=always 


Vous pouvez desormais consulter l'actuator à l'adresse localhost:8080/actuator

Exemples:



	{
		"_links": {
			"self": {
				"href": "http://localhost:8080/actuator",
				"templated": false
			},
			"health-path": {
				"href": "http://localhost:8080/actuator/health/{*path}",
				"templated": true
			},
			"health": {
				"href": "http://localhost:8080/actuator/health",
				"templated": false
			},
			"info": {
				"href": "http://localhost:8080/actuator/info",
				"templated": false
			}
		}
	}


**Custom endpoints**

Premierement, creer une class implementant HealthIndicator, dans le package actuator. Puis surcharger les methodes suivant se que vous avez besoin. Ne pas oublier l'annotation @Component de Spring.

Exemple:

	@Component
	public class PeopleHealthIndicator implements HealthIndicator {
		private final String message_key = "PeopleService";
		@Override
		public Health health() {
			if (!isRunningServicePeopleService()) {
				return Health.down().withDetail(message_key, "Not available").build();
			}
			return Health.up().withDetail(message_key, "Available").build();
		}
		private Boolean isRunningServicePeopleService() {
			Boolean isRunning = false;
			// Add real logic here to test if People Service is running; 
			// skipped for demo purposes
			// cad indiquer l'indicateur par exemple que vous voulez retourner
			return isRunning;
		}
	} 


Resultat: http://localhost:8080/actuator/health

	{
	"status": "DOWN",
	"components": {
		"db": {
			"status": "UP",
			"details": {
				"database": "H2",
				"result": 1,
				"validationQuery": "SELECT 1"
			}
		},
		"diskSpace": {
			"status": "UP",
			"details": {
			"total": 2000381014016,
			"free": 1968134787072,
			"threshold": 10485760
			}
		},
		"people": {
			"status": "DOWN",
			"details": {
			"PeopleService": "Not available"
			}
		},
		"ping": {
			"status": "UP"
			}
		}
	}



	
##Testing with Spring boot

Concept:

- Testing (Unit test, Integration tests)
- @SpringBootTest
- @WebMvcTest
- Mock environment
- TestRestTemplate

**Testing**

Ajouter tous d'abord la dependence test de spring boot

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		
Avec un scope à test

**Test Unitaire**

- Sont réalisable à l'aide de: JUnit, Mockito, Spring Test
- Test individuellement des unités de code (ex: methodes).
- Simulation de classe à l'aide d'annotation @MockBean


Methode:

1. On genere a l'aide de l'IDE la classe à tester en cliquant dessus puis New > JUnit Test Case. On selectionne les methodes à tester puis on valide.

2. Dans la classe généré dans le package test. Ajouter l'annotation  @RunWith(SpringRunner.class) et @WebMvcTest(TzaController.class) au dessus de la classe.

3. Ajouter l'annotation @MockBean au dessus des objets à bouchonner  (mock), ApplicationService et TicketService.

4. Creer un attribut membre à la classe MockMvc. Que vous cablez à Spring à l'aide de @Autowired

5. Dans les methodes à tester invoquer la methode perform de la classe MockMvc qui permet d'exécuter une demande et renvoyez un type qui permet d'enchaîner d'autres actions, telles que l'affirmation d'attentes, sur le résultat. Et y indiquer en parametre l'url ciblé (ex: "/tza/applications/").

6. Puis enchainer les teste souhaitaient.

Exemple:

	@RunWith(SpringRunner.class)
	@WebMvcTest(TzaController.class)
	public class TzaControllerUnitTest {
	    @Autowired
	    private MockMvc mockMvc;
	    @MockBean
	    ApplicationService applicationService;
	    @MockBean
	    TicketService ticketService;
	    @Test
	    public void getAllApplications() throws Exception {
       	mockMvc.perform(get("/tza/applications/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));
       	verify(applicationService, times(1)).listApplications();
    	}
	}


**ATTENTION** Si probleme copier/coller les importations


**@WebMvcTest**

Est une annotation utilisée pour les tests unitaires de la couche contrôleur

- Scans @Controller and @RestController
- Ne charge pas le contexte d'application entièrement
- Les dependance de bean doivent etre mocked
- Permet de tester rapidement une portion de l'application par petit chargement.



###Bug fixes 

**REGLE DE BASE ==> IL FAUT TOUJOURS REGARDER LE DERNIER CAUSE BY DANS LES LOGS D'ERREUR"**

####Application context

Afin de savoir "Comment configurer mes parametres de dépendances à travers le fichier applciation.properties ? ".
Il n'y a pas de solution toute faite.

Il faut consulter les git sources des dependances à l'aide de Maven repository, par exemple.

Puis, rechercher se dont l'on a besoin dans les fichier .properties, "alors la t'as du bol :o)".
Soit dans les fichier de proprietes gradle.properties ou application.yml ...


Remarques:

- Si une propriete du fichier application.properties n'est pas trouvé:

C'est peut etre que la dependance n'est pas mise dans le fichier maven .pom (ex: graphQl et graphiQl)
 

#### Maven dependency

Toujours se méfier des **SCOPE** des dependences copier/ coller du site maven repository.

Exemple: H2 par defaut est à test le changer en runtime


#### Server TOMCAT

Erreur 404 lors du démarrage TOMCAT

Cliquer sur propriete > General puis cliquer sur le bouton "Switch Location", celle-ci doit se mettre à jours.

Double-cliquer ensuite sur le server puis cocher dans la rubrique Server Locations sur la case > Use Tomcat installation (takes controle of Tomcat installation)

Votre server TOMCAT est maintenant operationnel à l'adresse URl: localhost:8080

#### Mode debug Spring

**RAPPEL** dans le fichier de configuration application.properties

	logging.level.org.springframework:debug

##### Erreur rencontrées

	***************************
	APPLICATION FAILED TO START
	***************************
	
	Description:
	
	Parameter 0 of method setTicketService in com.ruffin.Spring_Mvc_Restful_GraphQl.web.TzaController required a bean of type 'com.ruffin.Spring_Mvc_Restful_GraphQl.service.TicketServiceImpl' that could not be found.
	
	
	Action:
	
	Consider defining a bean of type 'com.ruffin.Spring_Mvc_Restful_GraphQl.service.TicketServiceImpl' in your configuration.

**==> RESOLUTION**

Il manquait l'annotation @Service à la classe TicketServiceImpl.class


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

#### JUnit

Si erreur :

	could not run test no tests found with test runner JUnit 5

Solution: 


Select your spring boot project name in Package Explorer
Right Click
Select "Run As"
Select "Run Configurations" in the sub menu
It will open a popup window
Select JUnit in the left panel
Select the project entries under JUnit
Right click on the project name and click delete

Si erreur: java.lang.IllegalStateException: javax.websocket.server.ServerContainer not available

Ajouter à l'annotation SpringBootTest le parametre suivant

	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


Si erreur : No qualifying bean of type available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}

Faire tres attention d'utiliser des interface et non des classe implementant l'interface lors du cablage (@Autowired) au risque d'avoir se message d'erreur lors des test

