package com.example.projeto_dick_bdiii.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto_dick_bdiii.domain.model.CentroDeCusto;
import com.example.projeto_dick_bdiii.domain.model.Usuario;

import java.util.List;


public interface CentroDeCustoRepository extends JpaRepository
<CentroDeCusto, Long>{
    List<CentroDeCusto> findByUsuario(Usuario usuario);
    
}
