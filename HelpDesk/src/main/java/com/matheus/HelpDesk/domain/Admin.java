package com.matheus.HelpDesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheus.HelpDesk.domain.DTOS.AdminDTO;
import com.matheus.HelpDesk.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Admin extends Pessoa{

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "admin")
    private List<Chamado> chamados = new ArrayList<Chamado>();

    public Admin(Integer id, String nome, String cpf, String email, String senha, List<Chamado> chamados) {
        super(id, nome, cpf, email, senha);
        this.chamados = chamados;
    }

    public Admin() {
        super();
        addPerfil(Perfil.ADMIN);
    }
    public Admin(AdminDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();


    }
    @JsonIgnore
    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
