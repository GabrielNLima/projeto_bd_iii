package com.example.projeto_dick_bdiii.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto_dick_bdiii.domain.model.Usuario;

public interface UsuarioRepository extends 
    JpaRepository<Usuario, Long>{
    
        Optional<Usuario> findByEmail(String email);
}
