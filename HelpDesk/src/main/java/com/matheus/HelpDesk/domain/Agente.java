package com.matheus.HelpDesk.domain;

import java.util.ArrayList;
import java.util.List;

public class Agente extends Pessoa {

    private List<Chamado> chamados = new ArrayList<Chamado>();

    public Agente() {
        super();
    }
    public Agente(Integer id, String nome, String cpf, String email, String senha){
        super(id, nome, cpf, email, senha);
    }

    public Agente(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
