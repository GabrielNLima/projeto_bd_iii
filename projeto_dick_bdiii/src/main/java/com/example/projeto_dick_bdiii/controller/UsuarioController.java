package com.example.projeto_dick_bdiii.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_dick_bdiii.domain.dto.usuario.UsuarioRequestdto;
import com.example.projeto_dick_bdiii.domain.dto.usuario.UsuarioResponsedto;
import com.example.projeto_dick_bdiii.domain.service.UsuarioService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponsedto>> obterTodos(){
        return ResponseEntity.ok(usuarioService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponsedto> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponsedto> cadastrar(@RequestBody UsuarioRequestdto dto){
        UsuarioResponsedto usuario = usuarioService.cadastrar(dto);
        return new ResponseEntity<UsuarioResponsedto>(usuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponsedto> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestdto dto){
        UsuarioResponsedto usuario = usuarioService.atualizar(id, dto);
        return new ResponseEntity<UsuarioResponsedto>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}