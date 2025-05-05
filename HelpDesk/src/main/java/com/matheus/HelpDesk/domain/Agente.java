package com.matheus.HelpDesk.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheus.HelpDesk.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Agente extends Pessoa {

    private static final long serialVersionUID = 1L;
    @OneToMany(mappedBy = "agente")
    private List<Chamado> chamados = new ArrayList<Chamado>();

    public Agente() {
        super();
        addPerfil(Perfil.AGENTE);
    }

    public Agente(Integer id, String nome, String cpf, String email, String senha){
        super(id, nome, cpf, email, senha);
    }


    public Agente(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    @JsonIgnore
    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
