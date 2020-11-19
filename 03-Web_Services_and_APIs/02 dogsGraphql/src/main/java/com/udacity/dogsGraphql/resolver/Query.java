package com.udacity.dogsGraphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udacity.dogsGraphql.entity.Dog;
import com.udacity.dogsGraphql.exception.DogNotFoundException;
import com.udacity.dogsGraphql.repository.DogRepository;

import java.util.Optional;

public class Query implements GraphQLResolver {

    private DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs(){
        return dogRepository.findAll();
    }

    public Dog findDogById(Long id) throws DogNotFoundException {
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if(optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            return dog;
        }else {
            throw  new DogNotFoundException("DogNotFound",id);
        }
    }

    public String findDogBreedById(Long id) throws DogNotFoundException {
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if(optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            return dog.getBreed();
        }else {
            throw  new DogNotFoundException("DogNotFound",id);
        }
    }

}
