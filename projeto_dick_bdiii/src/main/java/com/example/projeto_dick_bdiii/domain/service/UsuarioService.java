package com.example.projeto_dick_bdiii.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_dick_bdiii.domain.dto.usuario.UsuarioRequestdto;
import com.example.projeto_dick_bdiii.domain.dto.usuario.UsuarioResponsedto;
import com.example.projeto_dick_bdiii.domain.exception.BadRequestException;
import com.example.projeto_dick_bdiii.domain.exception.ResourceNotFoundException;
import com.example.projeto_dick_bdiii.domain.model.Usuario;
import com.example.projeto_dick_bdiii.domain.repository.UsuarioRepository;

@Service
public class UsuarioService implements 
    ICRUDService<UsuarioRequestdto, UsuarioResponsedto> {
    
    @Autowired
    private UsuarioRepository UsuarioRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<UsuarioResponsedto> obterTodos() {
        List<Usuario> usuarios = UsuarioRepository.findAll();
        return usuarios.stream().map(usuario -> 
        mapper.map(usuario,
         destinationTypeUsuarioResponsedto.class))
        .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponsedto obterPorId(Long id) {
        Optional<Usuario> usuario = UsuarioRepository.findById(id);
        if(usuario.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível obter o usuário com o id " + id);
        }
        return mapper.map(usuario,
        destinationType:UsuarioResponsedto.class);

    }

    @Override
    public UsuarioResponsedto cadastrar(UsuarioRequestdto dto) {
        if (dto.getEmail() == null || dto.getSenha() == null){
            throw new BadRequestException("Email e Senha são obrigatórios!");
        }
        Optional<Usuario> optUsuario = UsuarioRepository
        .findByEmail(dto.getEmail());
        if(optUsuario.isPresent()){
            throw new BadRequestException("Já existe um usuário com este email na base de dados!");
        }
        Usuario usuario = mapper.map(dto, destinationType:Usuario.class);
        usuario.setDataCadastro(new Date());;
        // criptografar senha
        usuario = UsuarioRepository.save(usuario);
        return mapper.map(usuario, destinationType:UsuarioResponsedto.class);
    }

    @Override
    public UsuarioResponsedto atualizar(Long id, UsuarioRequestdto dto) {
        obterPorId(id);
        if(optUsuario.isPresent()){
            throw new BadRequestException("Já existe um usuário com este email na base de dados!");
        }
        Usuario usuario = mapper
        .map(dto, destinationType:Usuario.class);
        usuario.setId(id);
        usuario = UsuarioRepository.save(usuario);
        return mapper
        .map(usuario, destinationType:UsuarioResponsedto.class);

    }

    @Override
    public void deletar(Long id) {
        Optional<Usuario> optUsuario = UsuarioRepository
        .findById(id);
        if(optUsuario.isEmpty()){
            throw new ResourceNotFoundException("Não foi possivel obter o usuario com o id.");
        };
    }
    Usuario usuario = optUsuario.get();
    usuario.setDataInativacao(new Date());
    UsuarioRepository.save(usuario);

}
