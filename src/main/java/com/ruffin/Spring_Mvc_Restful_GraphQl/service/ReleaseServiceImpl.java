package com.ruffin.Spring_Mvc_Restful_GraphQl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Release;
import com.ruffin.Spring_Mvc_Restful_GraphQl.repository.IReleaseRepository;

@Service
public class ReleaseServiceImpl implements IReleaseService {

	@Autowired
	private IReleaseRepository releaseRepository;
	
	@Override
	public List<Release> listReleases() {
		return (List<Release>) releaseRepository.findAll();
	}
}
