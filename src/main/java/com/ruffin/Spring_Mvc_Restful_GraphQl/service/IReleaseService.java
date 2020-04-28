package com.ruffin.Spring_Mvc_Restful_GraphQl.service;

import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Release;

public interface IReleaseService {

	Iterable<Release> listRelease();
}
