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
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<UsuarioResponsedto> obterTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario ->
        mapper.map(usuario, 
        UsuarioResponsedto.class))
        .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponsedto obterPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel obter o usuário com o id " + id);
        }
        return mapper.map(usuario,
        UsuarioResponsedto.class);
    }

    @Override
    public UsuarioResponsedto cadastrar(UsuarioRequestdto dto) {
        if (dto.getEmail() == null || dto.getSenha() == null){
            throw new BadRequestException("Email e Senha são Obrigatórios");
        }
        Optional<Usuario> optUsuario = usuarioRepository
        .findByEmail(dto.getEmail());
        if (optUsuario.isPresent()){
            throw new BadRequestException("Usuário existente com esse Email no Banco de Dados");
        }
        Usuario usuario = mapper 
        .map(dto, Usuario.class);
        usuario.setDataCadastro(new Date());
        //criptografar senha
        usuario = usuarioRepository.save(usuario);
        return mapper.map(usuario, UsuarioResponsedto.class);
    }

    @Override
    public UsuarioResponsedto atualizar(Long id, UsuarioRequestdto dto) {
        obterPorId(id);
        if (dto.getEmail() == null || dto.getSenha() == null){
            throw new BadRequestException("Email e Senha são Obrigatórios");
        }
        Usuario usuario = mapper 
        .map(dto, Usuario.class);
        usuario.setId(id);
        usuario = usuarioRepository.save(usuario);
        return mapper.map(usuario, UsuarioResponsedto.class);

    }

    @Override
    public void deletar(Long id) {
        Optional<Usuario> optUsuario = usuarioRepository
        .findById(id);
        if (optUsuario.isEmpty()){
            throw new ResourceNotFoundException("Não foi possivel obter o usuário com o id " + id);
        }
        Usuario usuario = optUsuario.get();
        usuario.setDataInativacao(new Date());
        usuarioRepository.save(usuario);
    }

    
}