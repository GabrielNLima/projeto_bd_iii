// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.projeto_dick_bdiii.domain.service;

import com.example.projeto_dick_bdiii.domain.dto.titulo.TituloRequestDTO;
import com.example.projeto_dick_bdiii.domain.dto.titulo.TituloResponseDTO;
import com.example.projeto_dick_bdiii.domain.exception.BadRequestException;
import com.example.projeto_dick_bdiii.domain.exception.ResourceNotFoundException;
import com.example.projeto_dick_bdiii.domain.model.Titulo;
import com.example.projeto_dick_bdiii.domain.model.Usuario;
import com.example.projeto_dick_bdiii.domain.repository.TituloRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TituloService implements ICRUDService<TituloRequestDTO, TituloResponseDTO> {
   @Autowired
   private TituloRepository tituloRepository;
   @Autowired
   private ModelMapper mapper;

   public TituloService() {
   }

   public List<TituloResponseDTO> obterTodos() {
      Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      List<Titulo> titulos = this.tituloRepository.findByUsuario(usuario);
      return (List)titulos.stream().map((titulo) -> {
         return (TituloResponseDTO)this.mapper.map(titulo, TituloResponseDTO.class);
      }).collect(Collectors.toList());
   }

   public TituloResponseDTO obterPorId(Long id) {
      Optional<Titulo> optTitulo = this.tituloRepository.findById(id);
      if (optTitulo.isEmpty()) {
         throw new ResourceNotFoundException("N\u00e3o foi poss\u00edvel encontrar o Titulo com o id = " + String.valueOf(id));
      } else {
         Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         if (((Titulo)optTitulo.get()).getUsuario().getId() != usuario.getId()) {
            throw new ResourceNotFoundException("O Titulo com id: " + String.valueOf(id) + "n\u00e3o pertence a este usu\u00e1rio");
         } else {
            return (TituloResponseDTO)this.mapper.map(optTitulo.get(), TituloResponseDTO.class);
         }
      }
   }

   public TituloResponseDTO cadastrar(TituloRequestDTO dto) {
      this.validarTitulo(dto);
      Titulo titulo = (Titulo)this.mapper.map(dto, Titulo.class);
      Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      titulo.setUsuario(usuario);
      titulo.setId((Long)null);
      titulo.setDataCadastro(new Date());
      titulo = (Titulo)this.tituloRepository.save(titulo);
      return (TituloResponseDTO)this.mapper.map(titulo, TituloResponseDTO.class);
   }

   public TituloResponseDTO atualizar(Long id, TituloRequestDTO dto) {
      this.obterPorId(id);
      this.validarTitulo(dto);
      Titulo titulo = (Titulo)this.mapper.map(dto, Titulo.class);
      Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      titulo.setUsuario(usuario);
      titulo.setId(id);
      titulo = (Titulo)this.tituloRepository.save(titulo);
      return (TituloResponseDTO)this.mapper.map(titulo, TituloResponseDTO.class);
   }

   public void deletar(Long id) {
      this.obterPorId(id);
      this.tituloRepository.deleteById(id);
   }

   private void validarTitulo(TituloRequestDTO dto) {
      if (dto.getTipo() == null || dto.getDataVencimento() == null || dto.getValor() == null || dto.getDescricao() == null) {
         throw new BadRequestException("Titulo Invalido - Campos Obrigat\u00f3rios n\u00e3o preenchidos");
      }
   }

   public List<TituloResponseDTO> obterPorDataVencimento (String periodoInicial, String periodoFinal){
      List<Titulo> titulos = tituloRepository.
      obterFluxoDeCaixaPorDataVencimento(periodoInicial, periodoFinal);
      return titulos.stream().map(titulo -> mapper
      .map(titulo, TituloResponseDTO.class)).collect(Collectors.toList());
   }
}
