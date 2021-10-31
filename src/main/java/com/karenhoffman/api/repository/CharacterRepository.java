package com.karenhoffman.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.karenhoffman.api.entity.DisneyCharacter;


@Repository
public interface CharacterRepository extends JpaRepository<DisneyCharacter, String>{
	
	
	
	
	@Query("SELECT c FROM DisneyCharacter c WHERE c.id = :idCharacter")
	public Optional<DisneyCharacter> findById(@Param("idCharacter") String idCharacter);
	
	@Query("SELECT c.name FROM DisneyCharacter c WHERE c.id != null")
	public List<String> nameAndImage();
	
	@Query("SELECT c FROM DisneyCharacter c WHERE c.name LIKE %:aux2%")
	public  Optional<DisneyCharacter>  findByName(@Param(value = "aux2") String aux2);
	
	@Query("SELECT c FROM DisneyCharacter c WHERE c.age = :aux2")
	public  Optional<DisneyCharacter> findByAge(@Param(value = "aux2") Integer aux2);
	

	@Query("SELECT c FROM DisneyCharacter c WHERE c.weight = :aux2")
	public  Optional<DisneyCharacter>  findByWeight(@Param(value = "aux2") Double aux2);
	
  
}
