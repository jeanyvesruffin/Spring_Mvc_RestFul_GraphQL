package com.ruffin.Spring_Mvc_Restful_GraphQl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Application;
import com.ruffin.Spring_Mvc_Restful_GraphQl.exception.ApplicationNotFoundException;
import com.ruffin.Spring_Mvc_Restful_GraphQl.repository.IApplicationRepository;

@Service
public class ApplicationServiceImpl implements IApplicationService {

	@Autowired
	private IApplicationRepository applicationRepository;

	@Override
	public List<Application> listApplications() {
		return (List<Application>) applicationRepository.findAll();
	}

	@Override
	public Application findApplication(long id) {
		Optional<Application> optionalApplication = applicationRepository.findById(id);

		if(optionalApplication.isPresent())
			return optionalApplication.get();
		else
			throw new ApplicationNotFoundException("Application Not Found");
	}

}
