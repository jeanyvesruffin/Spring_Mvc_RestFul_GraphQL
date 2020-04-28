package com.ruffin.Spring_Mvc_Restful_GraphQl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Application;
import com.ruffin.Spring_Mvc_Restful_GraphQl.repository.IApplicationRepository;

@Service
public class ApplicationServiceImpl implements IApplicationService {

	@Autowired
	private IApplicationRepository applicationRepository;
	
	@Override
	public Iterable<Application> listApplication() {
		return applicationRepository.findAll();
	}

}
