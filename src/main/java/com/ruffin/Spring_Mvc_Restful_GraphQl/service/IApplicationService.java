package com.ruffin.Spring_Mvc_Restful_GraphQl.service;

import java.util.List;

import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Application;

public interface IApplicationService {

	List<Application> listApplications();
    Application findApplication(long id);
}
