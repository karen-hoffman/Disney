package com.karenhoffman.app.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karenhoffman.app.entity.DisneyCharacter;
import com.karenhoffman.app.security.dto.NewUser;
import com.karenhoffman.app.security.entity.Rol;
import com.karenhoffman.app.security.entity.User;
import com.karenhoffman.app.security.enums.RolNombre;
import com.karenhoffman.app.service.CharacterServiceImplementation;
import com.karenhoffman.app.service.ErrorService;

@RestController
@RequestMapping("/character")
@CrossOrigin
public class CharacterController {
	
	private CharacterServiceImplementation characterServiceImplementation;
	
	@PostMapping("/create")
	public ResponseEntity<?> newCharacter(@Valid @RequestBody DisneyCharacter newCharacter, BindingResult bindingResult) throws ErrorService {
    	
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Campos mal o email invalido", HttpStatus.BAD_REQUEST);
        }
      
        characterServiceImplementation.createCharacter(newCharacter.getId(), newCharacter.getImg(), newCharacter.getName(), newCharacter.getAge(),  newCharacter.getWeight(), newCharacter.getHistory(), newCharacter.getMovies());
      
        return new ResponseEntity<>("Personaje creado", HttpStatus.CREATED);
    }

	

}
