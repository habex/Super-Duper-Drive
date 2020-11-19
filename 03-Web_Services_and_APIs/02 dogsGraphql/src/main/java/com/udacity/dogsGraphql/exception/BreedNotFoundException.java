package com.udacity.dogsGraphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreedNotFoundException extends Throwable implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    public BreedNotFoundException(String dogBreedNotFound, String invalidBreed) {
        super(dogBreedNotFound);
        extensions.put("invalidBreed",invalidBreed);
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
