// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.projeto_dick_bdiii.domain.service;

import com.example.projeto_dick_bdiii.domain.dto.centroDeCusto.CentroDeCustoRequestDTO;
import com.example.projeto_dick_bdiii.domain.dto.centroDeCusto.CentroDeCustoResponseDTO;
import com.example.projeto_dick_bdiii.domain.exception.ResourceNotFoundException;
import com.example.projeto_dick_bdiii.domain.model.CentroDeCusto;
import com.example.projeto_dick_bdiii.domain.model.Usuario;
import com.example.projeto_dick_bdiii.domain.repository.CentroDeCustoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CentroDeCustoService implements ICRUDService<CentroDeCustoRequestDTO, CentroDeCustoResponseDTO> {
   @Autowired
   private CentroDeCustoRepository repository;
   @Autowired
   private ModelMapper mapper;

   public CentroDeCustoService() {
   }

   public List<CentroDeCustoResponseDTO> obterTodos() {
      Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      List<CentroDeCusto> lista = this.repository.findByUsuario(usuario);
      return (List)lista.stream().map((centroDeCusto) -> {
         return (CentroDeCustoResponseDTO)this.mapper.map(centroDeCusto, CentroDeCustoResponseDTO.class);
      }).collect(Collectors.toList());
   }

   public CentroDeCustoResponseDTO obterPorId(Long id) {
      Optional<CentroDeCusto> optCentroDeCusto = this.repository.findById(id);
      if (optCentroDeCusto.isEmpty()) {
         throw new ResourceNotFoundException("N\u00e3o foi poss\u00edvel encontrar o Centro de Custo com o ID = " + String.valueOf(id));
      } else {
         Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         if (((CentroDeCusto)optCentroDeCusto.get()).getId() != usuario.getId()) {
            throw new ResourceNotFoundException("O Centro de Custo com o Id: " + String.valueOf(id) + "n\u00e3o pertence a este usuario");
         } else {
            return (CentroDeCustoResponseDTO)this.mapper.map(optCentroDeCusto.get(), CentroDeCustoResponseDTO.class);
         }
      }
   }

   public CentroDeCustoResponseDTO cadastrar(CentroDeCustoRequestDTO dto) {
      CentroDeCusto centroDeCusto = (CentroDeCusto)this.mapper.map(dto, CentroDeCusto.class);
      Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      centroDeCusto.setUsuario(usuario);
      centroDeCusto.setId((Long)null);
      centroDeCusto = (CentroDeCusto)this.repository.save(centroDeCusto);
      return (CentroDeCustoResponseDTO)this.mapper.map(centroDeCusto, CentroDeCustoResponseDTO.class);
   }

   public CentroDeCustoResponseDTO atualizar(Long id, CentroDeCustoRequestDTO dto) {
      this.obterPorId(id);
      CentroDeCusto centroDeCusto = (CentroDeCusto)this.mapper.map(dto, CentroDeCusto.class);
      Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      centroDeCusto.setUsuario(usuario);
      centroDeCusto.setId(id);
      centroDeCusto = (CentroDeCusto)this.repository.save(centroDeCusto);
      return (CentroDeCustoResponseDTO)this.mapper.map(centroDeCusto, CentroDeCustoResponseDTO.class);
   }

   public void deletar(Long id) {
      this.obterPorId(id);
      this.repository.deleteById(id);
   }
}
