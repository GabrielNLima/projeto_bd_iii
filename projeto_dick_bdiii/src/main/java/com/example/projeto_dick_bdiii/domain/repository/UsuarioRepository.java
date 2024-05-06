package com.example.projeto_dick_bdiii.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends 
    JpaRepository<Usuario, Long>{
    
        Optional<Usuario> findByEmail(String email);
}
