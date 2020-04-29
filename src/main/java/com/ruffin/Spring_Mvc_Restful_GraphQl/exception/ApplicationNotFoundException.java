package com.ruffin.Spring_Mvc_Restful_GraphQl.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

@SuppressWarnings("serial")
public class ApplicationNotFoundException extends RuntimeException implements GraphQLError {

	private Map<String, Object> extensions = new HashMap<>();

	public ApplicationNotFoundException(String message, Long invalidApplication) {
		super(message);
		extensions.put("Application invalide", invalidApplication);
	}

	public ApplicationNotFoundException() {
		super();
	}

	@Override
	public List<SourceLocation> getLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		// TODO Auto-generated method stub
		return null;
	}
}
