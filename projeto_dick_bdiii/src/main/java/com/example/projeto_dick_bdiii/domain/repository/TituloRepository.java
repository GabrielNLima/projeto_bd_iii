package com.example.projeto_dick_bdiii.domain.repository;

import com.example.projeto_dick_bdiii.domain.model.Titulo;
import com.example.projeto_dick_bdiii.domain.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TituloRepository extends JpaRepository<Titulo, Long> {
   List<Titulo> findByUsuario(Usuario usuario);
}
