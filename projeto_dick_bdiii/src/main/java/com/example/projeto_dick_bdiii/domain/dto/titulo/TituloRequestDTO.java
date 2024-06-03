// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.projeto_dick_bdiii.domain.dto.titulo;

import com.example.projeto_dick_bdiii.domain.Enum.ETipoTitulo;
import com.example.projeto_dick_bdiii.domain.dto.centroDeCusto.CentroDeCustoRequestDTO;
import java.util.Date;
import java.util.List;

public class TituloRequestDTO {
   private String descricao;
   private ETipoTitulo tipo;
   private List<CentroDeCustoRequestDTO> centrosDeCustos;
   private Double valor;
   private Date dataCadastro;
   private Date dataReferencia;
   private Date dataVencimento;
   private Date dataPagamento;
   private String observacao;

   public TituloRequestDTO() {
   }

   public String getDescricao() {
      return this.descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public ETipoTitulo getTipo() {
      return this.tipo;
   }

   public void setTipo(ETipoTitulo tipo) {
      this.tipo = tipo;
   }

   public List<CentroDeCustoRequestDTO> getCentrosDeCustos() {
      return this.centrosDeCustos;
   }

   public void setCentrosDeCustos(List<CentroDeCustoRequestDTO> centrosDeCustos) {
      this.centrosDeCustos = centrosDeCustos;
   }

   public Double getValor() {
      return this.valor;
   }

   public void setValor(Double valor) {
      this.valor = valor;
   }

   public Date getDataCadastro() {
      return this.dataCadastro;
   }

   public void setDataCadastro(Date dataCadastro) {
      this.dataCadastro = dataCadastro;
   }

   public Date getDataReferencia() {
      return this.dataReferencia;
   }

   public void setDataReferencia(Date dataReferencia) {
      this.dataReferencia = dataReferencia;
   }

   public Date getDataVencimento() {
      return this.dataVencimento;
   }

   public void setDataVencimento(Date dataVencimento) {
      this.dataVencimento = dataVencimento;
   }

   public Date getDataPagamento() {
      return this.dataPagamento;
   }

   public void setDataPagamento(Date dataPagamento) {
      this.dataPagamento = dataPagamento;
   }

   public String getObservacao() {
      return this.observacao;
   }

   public void setObservacao(String observacao) {
      this.observacao = observacao;
   }
}
