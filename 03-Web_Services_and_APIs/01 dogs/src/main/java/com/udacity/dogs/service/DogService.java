package com.udacity.dogs.service;

import com.udacity.dogs.entity.Dog;

import java.util.List;

public interface DogService {

    List<Dog> retrieveDogs();

    List<String> retrieveDogBreed();

    List<String> retrieveDogNames();

    String retrieveDogBreedById(Long id);

}