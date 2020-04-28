package com.ruffin.Spring_Mvc_Restful_GraphQl.service;

import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Application;

public interface IApplicationService {

	Iterable<Application> listApplication();
}
