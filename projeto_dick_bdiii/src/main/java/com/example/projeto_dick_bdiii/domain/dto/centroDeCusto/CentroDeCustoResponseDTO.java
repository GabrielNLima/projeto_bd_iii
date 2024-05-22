package com.example.projeto_dick_bdiii.domain.dto.centroDeCusto;

public class CentroDeCustoResponseDTO {
    private String descricao;
    private String observacao;
    private Long id;
    
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
