package com.matheus.HelpDesk.domain;

import com.matheus.HelpDesk.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Funcionario extends Pessoa {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "funcionario")

    protected List<Chamado> chamados = new ArrayList<Chamado>();

    public Funcionario() {
        super();
        addPerfil(Perfil.FUNCIONARIO);
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
