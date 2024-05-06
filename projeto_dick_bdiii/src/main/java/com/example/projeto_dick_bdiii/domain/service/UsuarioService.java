package com.example.projeto_dick_bdiii.domain.service;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_dick_bdiii.domain.dto.usuario.UsuarioRequestdto;
import com.example.projeto_dick_bdiii.domain.dto.usuario.UsuarioResponsedto;
import com.example.projeto_dick_bdiii.domain.exception.ResourceNotFoundException;

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
         destinationType:UsuarioResponsedto.class))
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrar'");
    }

    @Override
    public UsuarioResponsedto atualizar(Long id, UsuarioRequestdto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

}
