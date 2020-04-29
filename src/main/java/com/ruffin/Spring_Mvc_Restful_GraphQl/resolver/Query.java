package com.ruffin.Spring_Mvc_Restful_GraphQl.resolver;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Application;
import com.ruffin.Spring_Mvc_Restful_GraphQl.repository.IApplicationRepository;


@Component
public class Query implements GraphQLQueryResolver{

	private IApplicationRepository applicationRepository;

	public Query(IApplicationRepository applicationRepository) {
		super();
		this.applicationRepository = applicationRepository;
	}
	
	public Iterable<Application> findAllApplications(){	
		return applicationRepository.findAll();
	}
	public long countApplication() {
		return applicationRepository.count();
	}
	
}
