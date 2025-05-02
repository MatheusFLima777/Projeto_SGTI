package com.matheus.HelpDesk.domain;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Pessoa{

    private List<Chamado> chamados = new ArrayList<Chamado>();

    public Admin(Integer id, String nome, String cpf, String email, String senha, List<Chamado> chamados) {
        super(id, nome, cpf, email, senha);
        this.chamados = chamados;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
