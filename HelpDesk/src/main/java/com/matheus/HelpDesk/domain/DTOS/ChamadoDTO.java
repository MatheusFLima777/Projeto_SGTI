package com.matheus.HelpDesk.domain.DTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matheus.HelpDesk.domain.Chamado;
import com.matheus.HelpDesk.domain.enums.Prioridade;
import com.matheus.HelpDesk.domain.enums.Status;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ChamadoDTO implements Serializable {
    private static final long SerialVersionUID = 1L;

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura = LocalDateTime.now();
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;
    @NotNull(message = "Campo PRIORIDADE é Obrigatório!")
    private Prioridade prioridade;
    @NotNull(message = "Campo STATUS é requerido!")
    private Status status;
    @NotNull(message = "Campo TITULO é Obrigatório!")
    private String titulo;
    @NotNull(message = "Campo OBSERVAÇÕES é Obrigatório!")
    private String observacoes;
    private Integer agente;
    @NotNull(message = "Campo FUNCIONARIO é Obrigatório!")
    private Integer funcionario;
    private String nomeAgente;
    private String nomefuncionario;

    public ChamadoDTO() {
        super();
    }
    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade();
        this.status = obj.getStatus();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.agente = obj.getAgente().getId();
        this.funcionario = obj.getFuncionario().getId();
        this.nomeAgente = obj.getAgente().getNome();
        this.nomefuncionario = obj.getFuncionario().getNome();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getAgente() {
        return agente;
    }

    public void setAgente(Integer agente) {
        this.agente = agente;
    }

    public Integer getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Integer funcionario) {
        this.funcionario = funcionario;
    }

    public String getNomeAgente() {
        return nomeAgente;
    }

    public void setNomeAgente(String nomeAgente) {
        this.nomeAgente = nomeAgente;
    }

    public String getNomefuncionario() {
        return nomefuncionario;
    }

    public void setNomefuncionario(String nomefuncionario) {
        this.nomefuncionario = nomefuncionario;
    }
}

