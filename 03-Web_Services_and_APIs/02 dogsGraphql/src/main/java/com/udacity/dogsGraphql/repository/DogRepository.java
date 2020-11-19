package com.udacity.dogsGraphql.repository;


import com.udacity.dogsGraphql.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {


   // @Query("Insert into Dod (name,breed,origin) Values (name,breed,origin)")
   // Dog newDog(Dog dog);

    //@Query("Select d.breed From Dog d")
    //List<String> findAllBreeds();

    //@Query("Select d.breed From Dog d Where d.id =:id")
   // Dog findDogById(Long id);


    //@Query("Select d.name From Dog d")
    //List<String> findAllNames();

    //@Query("Select d.breed From Dog d Where d.id =:id")
    //String findBreedById(Long id);

    //@Query("Delete Dog d Where d.id =:id")
    //Boolean deleteDog(Long id);

    //@Query("Update Dog d set d.name = name Where d.id =:id")
    //Dog updateDogName( Dog dog);


}
