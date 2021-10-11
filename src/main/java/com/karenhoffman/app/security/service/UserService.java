package com.karenhoffman.app.security.service;


import com.karenhoffman.app.security.entity.User;
import com.karenhoffman.app.security.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;
    

    public Optional<User> getByUsername(String nombreUsuario){
        return userRepository.findByUsername(nombreUsuario);
    }

    public Boolean existsByUsuario(String nombreUsuario){
        return userRepository.existsByUsername(nombreUsuario);
    }

    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(User usuario){
        userRepository.save(usuario);
    }


}