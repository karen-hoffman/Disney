package com.karenhoffman.app.service;

import java.util.ArrayList;
import java.util.List;

import com.karenhoffman.app.entity.Movie;

public interface CharacterService {
	
    
    public void createCharacter(String idUser, byte[] img, String name, Integer age, Double weight,
          String historia, List<Movie> movies) throws ErrorService;
    
    public void modifyCharacter(String idUser, String idCharacter, byte[] img, String name, Integer age, Double weight,
          String history, ArrayList<Movie> movies) throws ErrorService;
    
    public void deleteCharacter(String idUser, String idPersonaje) throws ErrorService; 
    
    public Character buscarPorId(String id);
    
    public void validate(byte[] img, String name, Integer age, Double weight, String history, List<Movie> movies) throws ErrorService;
}
