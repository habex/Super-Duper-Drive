package com.udacity.dogsGraphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DogNotFoundException extends Throwable implements GraphQLError {

    private Map<String,Object> extensions = new HashMap<>();

    public DogNotFoundException(String dogNotFound, Long inValidDogId) {
    super(dogNotFound);
    extensions.put("inValidDogId",inValidDogId);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    public Map<String, Object> getExtensions(){
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
