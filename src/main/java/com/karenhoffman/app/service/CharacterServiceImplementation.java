package com.karenhoffman.app.service;

import java.util.ArrayList;
import java.util.List;

import com.karenhoffman.app.entity.DisneyCharacter;
import com.karenhoffman.app.entity.Movie;
import com.karenhoffman.app.repository.CharacterRepository;


public class CharacterServiceImplementation implements CharacterService {
	
	
	private CharacterRepository characterRepository;
	
	@Override
	public void createCharacter(String idUser, byte[] img, String name, Integer age, Double weight, String history,
			List<Movie> movies) throws ErrorService {
		
      validate(img, name, age, weight, history, movies);

      DisneyCharacter character = new DisneyCharacter();
      character.setAge(age);
      character.setHistory(history);
      character.setName(name);
      character.setWeight(weight);
      character.setMovies(movies);
      character.setImg(img);
      characterRepository.save(character);
		
	}
	

	@Override
	public void modifyCharacter(String idUser, String idCharacter, byte[] img, String name, Integer age, Double weight,
			String history, ArrayList<Movie> movies) throws ErrorService {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCharacter(String idUser, String idPersonaje) throws ErrorService {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Character buscarPorId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate(byte[] img, String name, Integer age, Double weight, String history, List<Movie> movies)
			throws ErrorService {
		if (name.isEmpty() || name == null) {
	        throw new ErrorService("El nombre del personaje no puede ser nulo");
	        }

	   if (history.isEmpty() || history == null) {
	        throw new ErrorService("La historia no puede ser nula");
	    }

	    
	    if (age == null) {
	        throw new ErrorService("La edad no puede ser nula");
	    }

	    if (weight == null) {
	        throw new ErrorService("El peso no puede ser nula");
	    }
	    if (movies.isEmpty()) {
	        throw new ErrorService("Debe asignar una lista de peliculas o series");
	   }
		
	}

}
