package com.karenhoffman.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.karenhoffman.api.entity.Genre;
import com.karenhoffman.api.entity.Movie;

import com.karenhoffman.api.repository.GenreRepository;

@Service
public class GenreService {
	
	@Autowired
	private GenreRepository genreRepository;

	
	public List<Genre> list() {
		return genreRepository.findAll();
	}
	
	public Genre findById(String id) {
		return genreRepository.getById(id);
	}
	
	public void createGenre(String name, List<Movie> movies) {
	
	 try {
      
	      validate(name, movies);

	      Genre genre = new Genre();
	      genre.setId(name);
	      genreRepository.save(genre);
	   
		 }
		 catch (Exception e) {
		 	 e.getMessage();	 	
		}
	 }
	

	
	public void validate(String name, List<Movie> movies)
			throws Exception {
		try {
			
			if (name.isEmpty() || name == null) {
		        throw new Exception("El nombre del personaje no puede ser nulo");
		        }

		   if (movies.isEmpty() || movies == null) {
		        throw new Exception("La historia no puede ser nula");
		    }

		    
		  
			
		} catch (Exception e) {
			 e.getMessage();
		}


	}
	
}
