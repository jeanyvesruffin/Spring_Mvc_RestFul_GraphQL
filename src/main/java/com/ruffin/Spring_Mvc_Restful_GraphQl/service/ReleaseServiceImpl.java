package com.ruffin.Spring_Mvc_Restful_GraphQl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Release;
import com.ruffin.Spring_Mvc_Restful_GraphQl.repository.IReleaseRepository;

@Service
public class ReleaseServiceImpl implements IReleaseService {

	@Autowired
	private IReleaseRepository releaseRepository;
	
	@Override
	public Iterable<Release> listRelease() {
		return releaseRepository.findAll();
	}

}
