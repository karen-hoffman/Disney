package com.karenhoffman.api.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.karenhoffman.api.entity.DisneyCharacter;
import com.karenhoffman.api.entity.Movie;
import com.karenhoffman.api.repository.CharacterRepository;

@Service
public class CharacterService  {
	
	@Autowired
	private CharacterRepository characterRepository;

	
	public List<DisneyCharacter> findAll() {
		return characterRepository.findAll();
	}
	
	public List<String> nameAndImage() {
		return characterRepository.nameAndImage();
	}
	
	public  ResponseEntity<?>  find(String aux) {
		String name = "";
		List<DisneyCharacter> characters = new ArrayList<DisneyCharacter>();
		
	try {
		try {
			Integer entero = Integer.parseInt(aux);
			Optional<DisneyCharacter> c = characterRepository.findByAge(entero);
			if(c.isPresent()) {
				characters.add(c.get());
			}
			return new ResponseEntity<>(characters, HttpStatus.ACCEPTED);
		}
		catch (Exception e) {
			try{
				
				Double aux2 = Double.parseDouble(aux);
				Optional<DisneyCharacter> c = characterRepository.findByWeight(aux2);
				if(c.isPresent()) {
					characters.add(c.get());
				}
				return new ResponseEntity<>(characters, HttpStatus.ACCEPTED);
			}
			catch (Exception ex) {
				aux = name;
				Optional<DisneyCharacter> c = characterRepository.findByName(name);
				if(c.isPresent()) {
					characters.add(c.get());
				}
				return new ResponseEntity<>(characters, HttpStatus.ACCEPTED);
			}
		}
	
		
		
	} catch (Exception e) {
		e.getMessage();
		
	} 
	
	return null;
	
}
	
	
	@Transactional
	public void delete(DisneyCharacter character) {
		characterRepository.delete(character);
	}
	
	@Transactional
	public void update(DisneyCharacter character) {
		try {
			Optional<DisneyCharacter> c = characterRepository.findById(character.getId());
			if(!c.isPresent()) {
	        	throw new Exception("El id proporcionado no corresponde a un personaje existente");
	        } else {
	        	if (c.isPresent()) {
	        		DisneyCharacter ch = c.get();
	        		ch.setAge(character.getAge());
	        		ch.setHistory(character.getHistory());
	        		ch.setMovies(character.getMovies());
	        		ch.setName(character.getName());
	        		ch.setWeight(character.getWeight());
	        		characterRepository.saveAndFlush(ch);
	        	}
	        }
	      
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	
	public Optional<DisneyCharacter> findById(String id) {
		try {
			return characterRepository.findById(id);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
		
	}
	
	@Transactional
	public void createCharacter(String name, Integer age, Double weight,
          String history, List<Movie> movies)  {
	
	 try {
      
		
	      validate(name, age, weight, history);

	    
	      DisneyCharacter character = new DisneyCharacter();
	      character.setAge(age);
	      character.setHistory(history);
	      character.setName(name);
	      character.setMovies(movies);
	      character.setWeight(weight);
	      characterRepository.save(character);
	   
		 }
		 catch (Exception e) {
		 	 e.getMessage();	 	
		}
	 }
	

	public void save(DisneyCharacter c) {
		characterRepository.save(c);
	}
	public void saveAndFlush(DisneyCharacter c) {
		characterRepository.saveAndFlush(c);
	}

	public void validate(String name, Integer age, Double weight, String history)
			throws Exception {
		try {
			
			if (name.isEmpty() || name == null) {
		        throw new Exception("El nombre del personaje no puede ser nulo");
		        }

		   if (history.isEmpty() || history == null) {
		        throw new Exception("La historia no puede ser nula");
		    }

		    
		    if (age == null) {
		        throw new Exception("La edad no puede ser nula");
		    }

		    if (weight == null) {
		        throw new Exception("El peso no puede ser nulo");
		    }
		 
		   
		  
			
		} catch (Exception e) {
			 e.getMessage();
		}
		
	}




}
