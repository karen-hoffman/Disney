package com.karenhoffman.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.karenhoffman.api.entity.Genre;
import com.karenhoffman.api.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
		
		
	@Query("SELECT m FROM Movie m WHERE m.id = :idMovie")
	public Optional<Movie> findById(@Param("idMovie") String idMovie);
	
	
	@Query("SELECT m FROM Movie m WHERE m.id != null")
	public List<Movie> findAll();
	
	@Query("SELECT m.title FROM Movie m WHERE m.id != null")
	public List<String> nameAndImage();
	
	@Query("SELECT m FROM Movie m WHERE m.title LIKE %:aux%")
	public  List<Movie>  findByTitle(@Param(value = "aux") String aux);
	
	@Query("SELECT m FROM Movie m ORDER BY m.creation ASC")
	public  List<Movie> orderByCreationASC();
	
	@Query("SELECT m FROM Movie m ORDER BY m.creation ASC")
	public  List<Movie> orderByCreationDESC();
	
	@Query("SELECT m FROM Movie m WHERE m.genre = :genre")
	public  List<Movie>  findByGenre(@Param(value = "genre") Genre genre);
	
	
	
	
}


