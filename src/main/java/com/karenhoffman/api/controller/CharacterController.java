package com.karenhoffman.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karenhoffman.api.entity.DisneyCharacter;
import com.karenhoffman.api.entity.Movie;
import com.karenhoffman.api.service.CharacterService;
import com.karenhoffman.api.service.MovieService;

@RestController
@RequestMapping("/character")
@CrossOrigin
public class CharacterController {

	@Autowired
	private CharacterService characterService;

	@Autowired
	private MovieService movieService;

	@PostMapping("/create")
	public ResponseEntity<?> newCharacter(@Valid @RequestBody DisneyCharacter character, BindingResult bindingResult)
		{

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>("Campos mal o emai incorrecto", HttpStatus.BAD_REQUEST);
		}

		for (Movie m : character.getMovies()) {

			if (!movieService.findById(m.getId()).isPresent()) {
				movieService.save(m);
			}
		}

		characterService.createCharacter(character.getName(), character.getAge(), character.getWeight(),
				character.getHistory(), character.getMovies());

		return new ResponseEntity<>("Perosnaje creado", HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateCharacter(@Valid @RequestBody DisneyCharacter character, BindingResult bindingResult)
			 {
		try {

			if (bindingResult.hasErrors()) {
				return new ResponseEntity<>("Campos mal o emai incorrecto", HttpStatus.BAD_REQUEST);
			}

			for (Movie m : character.getMovies()) {

				if (movieService.findById(m.getId()) == null) {
					movieService.save(m);
				}
			}

			characterService.update(character);
			return new ResponseEntity<>("Perosnaje actualizado", HttpStatus.CREATED);
		} catch (Exception e) {
			e.getMessage();
			return new ResponseEntity<>("El personaje no se pudo actualizar", HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping("/delete/{idCharacter}")
	public void deleteCharacter(@PathVariable("idCharacter") String idCharacter) throws Exception {

		Optional<DisneyCharacter> character = characterService.findById(idCharacter);

		if (character.isPresent()) {
			DisneyCharacter c = character.get();
			characterService.delete(c);

		} else {
			throw new Exception("No se encontro el personaje");

		}

	}

	@GetMapping("/detail")
	public List<DisneyCharacter> listAll() {
		
		Iterable<DisneyCharacter> characters= characterService.findAll();
	    List<DisneyCharacter> list = new ArrayList<DisneyCharacter>();
		characters.forEach(list::add);
		return list;
		    
	}
	
	@GetMapping("/characters")
	public List<String> nameAndImage() {
		
		Iterable<String> characters= characterService.nameAndImage();
	    List<String> list = new ArrayList<String>();
		characters.forEach(list::add);
		return list;
		    
	}
	
	@GetMapping("/findCharacters")
	public  ResponseEntity<?>  find(@RequestParam(required = false) String name, @RequestParam(required = false) String age, @RequestParam(required = false) String weight ){
		
		try {
			
			return characterService.find(age);
		}
		catch (Exception e) {
			try{
			
				return characterService.find(weight);
			}
			catch (Exception ex) {
				return characterService.find(name);
			}
		}
							

	}
}
