package com.karenhoffman.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.karenhoffman.app.entity.DisneyCharacter;



public interface CharacterRepository extends JpaRepository<DisneyCharacter, String>{
	
	
	
	/*@Query("SELECT c FROM Character c WHERE c.name = :name")
    public Character findByName(@Param("name")String name);

    @Query("SELECT c FROM Character c WHERE c.age = :age")
    public Character SelectByAge(@Param("edad")Integer age);

    @Query("SELECT c FROM Character c WHERE c.weight = :weight")
    public Character SelectByWeight(@Param("weight") Double weight);
  
  
    @Query("SELECT c FROM Character c WHERE c.Movie.id = :id")
    public Character SelectByIdMovie(@Param("id") String id);

    @Query("SELECT c.nombre, c.imagen FROM Character c WHERE c.id != null")
    public Character SelectAllCharacters();
 
   @Query("SELECT c.img, c.name, c.age, c.weight, c.history, c.movies FROM  c WHERE c.id != null")
   public Character DitailCharacter(Character c);*/
}
