package com.example.projeto_dick_bdiii.domain.dto.usuario;

public class LoginResponsedto {
    private String token;
    private UsuarioResponsedto usuarioResponsedto;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UsuarioResponsedto getUsuarioResponsedto() {
        return usuarioResponsedto;
    }
    public void setUsuarioResponsedto(UsuarioResponsedto usuarioResponsedto) {
        this.usuarioResponsedto = usuarioResponsedto;
    }    
}
