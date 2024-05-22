package com.example.projeto_dick_bdiii.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.projeto_dick_bdiii.domain.dto.centroDeCusto.CentroDeCustoRequestDTO;
import com.example.projeto_dick_bdiii.domain.dto.centroDeCusto.CentroDeCustoResponseDTO;
import com.example.projeto_dick_bdiii.domain.exception.ResourceNotFoundException;
import com.example.projeto_dick_bdiii.domain.model.CentroDeCusto;
import com.example.projeto_dick_bdiii.domain.model.Usuario;
import com.example.projeto_dick_bdiii.domain.repository.CentroDeCustoRepository;

@Service
public class CentroDeCustosService implements ICRUDService
<CentroDeCustoRequestDTO, CentroDeCustoResponseDTO> {
    @Autowired
    private CentroDeCustoRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CentroDeCustoResponseDTO> obterTodos() {
        Usuario usuario = (Usuario) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();
        List<CentroDeCusto> lista = repository.findByUsuario(usuario);
        return lista.stream().map(centroDeCusto -> mapper.map(centroDeCusto, CentroDeCustoResponseDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public CentroDeCustoResponseDTO obterPorId(Long id) {
        Optional<CentroDeCusto> optCentroDeCusto = repository
        .findById(id);
        if(optCentroDeCusto.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar o Centro de Custo com o id: " + id);
        }
        return mapper.map(optCentroDeCusto.get(), CentroDeCustoResponseDTO.class);
    }

    @Override
    public CentroDeCustoResponseDTO cadastrar(CentroDeCustoRequestDTO dto) {
        CentroDeCusto centroDeCusto = mapper.map(dto, CentroDeCusto.class);
        Usuario usuario = (Usuario) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();
        centroDeCusto.setUsuario(usuario);
        centroDeCusto.setId(null);
        centroDeCusto = repository.save(centroDeCusto);
        return mapper.map(centroDeCusto,
        CentroDeCustoResponseDTO.class);
    }

    @Override
    public CentroDeCustoResponseDTO atualizar(Long id, CentroDeCustoRequestDTO dto) {
        obterPorId(id);
        CentroDeCusto centroDeCusto = mapper.map(dto, CentroDeCusto.class);
        Usuario usuario = (Usuario) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();
        centroDeCusto.setUsuario(usuario);
        centroDeCusto.setId(id);
        centroDeCusto = repository.save(centroDeCusto);
        return mapper.map(centroDeCusto,
        CentroDeCustoResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
        obterPorId(id);
        repository.deleteById(id);
    }
    
}