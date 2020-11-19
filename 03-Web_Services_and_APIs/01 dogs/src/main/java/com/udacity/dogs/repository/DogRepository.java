package com.udacity.dogs.repository;

import com.udacity.dogs.entity.Dog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DogRepository extends CrudRepository<Dog,Long> {


    @Query("Select d.breed From Dog d")
    List<String> findAllBreed();

    @Query("Select d.name From Dog d")
    List<String> findAllName();


    @Query("Select d.breed From Dog d Where d.id =:id")
    String findBreedById( Long id);

}
