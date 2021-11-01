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
import org.springframework.web.multipart.MultipartFile;

import com.karenhoffman.api.entity.DisneyCharacter;
import com.karenhoffman.api.entity.Movie;
import com.karenhoffman.api.service.CharacterService;
import com.karenhoffman.api.service.MovieService;

@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieController {
	@Autowired
	private CharacterService characterService;

	@Autowired
	private MovieService movieService;

	@PostMapping("/create")
	public ResponseEntity<?> newMovie(@Valid @RequestBody Movie movie, @RequestParam MultipartFile file, BindingResult bindingResult)
			throws Exception{

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>("Campos mal o emai incorrecto", HttpStatus.BAD_REQUEST);
		}
		
		if (movie.getQualification() < 1 || movie.getQualification() > 5) {
			return new ResponseEntity<>("La calificación de la película debe estar entre 1 y 5", HttpStatus.BAD_REQUEST);
		}
		
		for (DisneyCharacter character : movie.getCharacters()) {
			
			if (!characterService.findById(character.getId()).isPresent()){
				characterService.saveAndFlush(character);
				
			}
		}

		movieService.createMovie(movie.getTitle(), movie.getCreation(), movie.getQualification(), movie.getCharacters(), movie.getImg(), movie.getGenre(), file);

		return new ResponseEntity<>("Pelicula o serie creada", HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateMovie(@Valid @RequestBody Movie movie, BindingResult bindingResult)
			throws Exception{
		try {

			if (bindingResult.hasErrors()) {
				return new ResponseEntity<>("Campos mal o emai incorrecto", HttpStatus.BAD_REQUEST);
			}

			for (DisneyCharacter character : movie.getCharacters()) {

				if (!characterService.findById(character.getId()).isPresent()) {
					character.setId(null);
					characterService.saveAndFlush(character);
				}
			}

			movieService.update(movie);
			return new ResponseEntity<>("Pelicula actualizada", HttpStatus.CREATED);
		} catch (Exception e) {
			e.getMessage();
			return new ResponseEntity<>("La pelicula no se pudo actualizar", HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping("/delete/{idMovie}")
	public void deleteMovie(@PathVariable("idMovie") String idMovie) throws Exception {
		
		Optional<Movie> movie = movieService.findById(idMovie);

		if (movie.isPresent()) {
		
			movieService.delete(movie.get());

		} else {
			throw new Exception("No se pudo eliminar la pelicula");

		}

	}
	
	@GetMapping("/detail")
	public List<Movie> listAll() {
		
		Iterable<Movie> movies= movieService.findAll();
	    List<Movie> list = new ArrayList<Movie>();
		movies.forEach(list::add);
		return list;
		    
	}
	
	@GetMapping("/findByGenre")
	public ResponseEntity<?> findByGenre(@RequestParam String idGenre) {
		try {
			
			return new ResponseEntity<>(movieService.findByGenre(idGenre), HttpStatus.OK);	
			
		}
		catch (Exception e) {
			e.getMessage();	
		}
		
		return new ResponseEntity<>("Ocurrio un problema con la búsqueda", HttpStatus.OK);	
		
	}
	
	
	@GetMapping("/findMovies")
	public  ResponseEntity<?> find(@RequestParam(required = false) String title, @RequestParam(required = false) String order){
		
		try {
			if(title!=null) {
				List<Movie> movies = movieService.findByTitle(title);
				return new ResponseEntity<>(movies, HttpStatus.OK);
			}
			
			if (order!= null) {
				if(order.equalsIgnoreCase("ASC")) {
					List<Movie> movies = movieService.orderByCreationASC();
					return new ResponseEntity<>(movies, HttpStatus.OK);
				} else {
					List<Movie> movies = movieService.orderByCreationDESC();
					return new ResponseEntity<>(movies, HttpStatus.OK);
				}
				
			}
		}
		catch (Exception e) {
			e.getMessage();	
		}
							
		return new ResponseEntity<>("Ha ocurrido un error", HttpStatus.CONFLICT);
	}
	
		
  }
