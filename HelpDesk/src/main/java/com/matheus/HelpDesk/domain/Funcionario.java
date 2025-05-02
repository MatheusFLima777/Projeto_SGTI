package com.matheus.HelpDesk.domain;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa {

    private List<Chamado> chamados = new ArrayList<Chamado>();

    public Funcionario() {
        super();
    }
    public Funcionario(Integer id, String nome, String cpf, String email, String senha){
        super(id, nome, cpf, email, senha);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
