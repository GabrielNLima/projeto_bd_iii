package com.example.projeto_dick_bdiii.domain.dto.dashboard;

import java.util.List;

import com.example.projeto_dick_bdiii.domain.dto.titulo.TituloResponseDTO;

public class DashboardResponseDTO {
    private double totalPagar;
    private double totalReceber;
    private double saldo;
    private List<TituloResponseDTO> TitulosPagar;
    private List<TituloResponseDTO> TitulosReceber;

    public DashboardResponseDTO(){}

    public DashboardResponseDTO(Double totalPagar, Double totalReceber,
    Double saldo, List<TituloResponseDTO> TitulosPagar, List<TituloResponseDTO> TitulosReceber){
        this.totalPagar = totalPagar;
        this.totalReceber = totalReceber;
        this.saldo = saldo;
        this.TitulosPagar = TitulosPagar;
        this.TitulosReceber = TitulosReceber;
    }

    public double getTotalPagar() {
        return totalPagar;
    }
    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }
    public double getTotalReceber() {
        return totalReceber;
    }
    public void setTotalReceber(double totalReceber) {
        this.totalReceber = totalReceber;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public List<TituloResponseDTO> getTitulosPagar() {
        return TitulosPagar;
    }
    public void setTitulosPagar(List<TituloResponseDTO> titulosPagar) {
        TitulosPagar = titulosPagar;
    }
    public List<TituloResponseDTO> getTitulosReceber() {
        return TitulosReceber;
    }
    public void setTitulosReceber(List<TituloResponseDTO> titulosReceber) {
        TitulosReceber = titulosReceber;
    }

}
