package com.example.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Signin;

public interface SigninRepository extends JpaRepository<Signin,Long> {

    Optional<Signin> findByEmail(String email);
    
}
