package com.karenhoffman.app.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karenhoffman.app.security.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findById(int idUser);
    Optional<User> findByUsername(String username);
    boolean existsByUsername (String username);
    boolean existsByEmail (String email);
}