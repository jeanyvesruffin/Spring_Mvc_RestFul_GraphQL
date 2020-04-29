package com.ruffin.Spring_Mvc_Restful_GraphQl.mutator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Application;
import com.ruffin.Spring_Mvc_Restful_GraphQl.exception.ApplicationNotFoundException;
import com.ruffin.Spring_Mvc_Restful_GraphQl.repository.IApplicationRepository;


@Component
public class Mutation implements GraphQLMutationResolver {

	private IApplicationRepository applicationRepository;

	public Mutation(IApplicationRepository applicationRepository) {
		super();
		this.applicationRepository = applicationRepository;
	}
	/**
	 * 
	 * @param name
	 * @param owner
	 * @param description
	 * @return la creation d'une nouvelle application en base de donnée
	 */
	public Application newApplication(String name, String owner, String description) {
		Application app = new Application(name, description, owner);
		applicationRepository.save(app);
		return app;
	}

	/**
	 * 
	 * @param id
	 * @return Supprime l'application
	 */
	public boolean deleateApplication(Long id) {
		applicationRepository.deleteById(id);
		return true;
	}

	/**
	 * 
	 * @param owner
	 * @param id
	 * @return L'application avec l'owner mise à jours
	 */
	public Application updateApplication(String owner, Long id) {
		Optional<Application> optionalApplication = applicationRepository.findById(id);
		if(optionalApplication.isPresent()) {
			Application application = optionalApplication.get();
			application.setOwner(owner);
			applicationRepository.save(application);
			return application;
		} else {
			throw new ApplicationNotFoundException("L'application n'as pas été trouvé id : ", id);
		}
	}
}
