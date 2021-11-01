package com.karenhoffman.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.karenhoffman.api.entity.DisneyCharacter;
import com.karenhoffman.api.entity.Genre;
import com.karenhoffman.api.entity.Image;
import com.karenhoffman.api.entity.Movie;
import com.karenhoffman.api.repository.GenreRepository;
import com.karenhoffman.api.repository.ImageRepository;
import com.karenhoffman.api.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private ImageService imageService;


	@Transactional
	public void delete(Movie movie) {
		movieRepository.delete(movie);
	}

	@Transactional
	public void createMovie(String title, Date creation, Integer qualification, List<DisneyCharacter> characters, Image image,
			Genre genre, MultipartFile file) throws Exception {

		try {

			validate(title, creation, qualification, characters);

			Movie movie = new Movie();
			movie.setTitle(title);
			movie.setCreation(creation);
			movie.setQualification(qualification);
			movie.setCharacters(characters);
			movie.setGenre(genre);
			
			movie.setImg(imageService.save(file));

			movieRepository.save(movie);

		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Transactional
	public void update(Movie movie) throws Exception {
		try {
			Optional<Movie> m = movieRepository.findById(movie.getId());
			if (!m.isPresent()) {
				throw new Exception("El id proporcionado no corresponde a una pelicula existente");
			} else {
				Movie mo = m.get();
				mo.setQualification(movie.getQualification());
				mo.setCreation(movie.getCreation());
				mo.setCharacters(movie.getCharacters());
				mo.setTitle(movie.getTitle());

				movieRepository.saveAndFlush(mo);
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	public Optional<Movie> findById(String id) {

		return movieRepository.findById(id);

	}

	public void save(Movie movie) {
		movieRepository.save(movie);
	}

	public void saveAndFlush(Movie movie) {
		movieRepository.saveAndFlush(movie);
	}

	public List<Movie> findByTitle(String title) {

		try {
			return movieRepository.findByTitle(title);
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	public List<Movie> findByGenre(String idGenre) {

		try {
			Optional<Genre> genre = genreRepository.findById(idGenre);
			if (genre.isPresent()) {
				return movieRepository.findByGenre(genre.get());
			} else {
				return null;
			}
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	public List<Movie> orderByCreationASC() {

		try {

			return movieRepository.orderByCreationASC();

		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	public List<Movie> orderByCreationDESC() {

		try {
			return movieRepository.orderByCreationDESC();

		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	public void validate(String title, Date creation, Integer qualification, List<DisneyCharacter> characters)
			throws Exception {

		try {

			if (title.isEmpty() || title == null) {
				throw new Exception("El nombre de la pelicula o serie no puede ser nulo");
			}

			if (qualification == null || qualification < 1 || qualification > 5) {
				throw new Exception("La calificación no puede ser nula y debe estar entre los valores 1 y 5");
			}

			if (creation == null) {
				throw new Exception("Debe ingresar una fecha de creación");
			}

			if (characters.isEmpty()) {
				throw new Exception("Debe asignar una lista de peliculas o series");
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}

}
