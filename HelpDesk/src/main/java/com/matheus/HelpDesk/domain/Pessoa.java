package com.matheus.HelpDesk.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.matheus.HelpDesk.domain.enums.Perfil;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity

public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*Anotação para mostrar que
                                                         a responsabilidade da geração do ID fica por conta do BD */
     protected Integer id;
     protected String nome;

     @Column(unique = true)
     protected String cpf;
    @Column(unique = true)
     protected String email;
     protected String senha;
     @ElementCollection(fetch = FetchType.EAGER)
     /* Para ter certeza que o usuário tenha a lista de perfil, junto ao usuário, para quando dar o Get vir com o perfil correto ao usuário*/
    @CollectionTable(name = "TB_PERFIS")
     protected Set<Integer> perfis = new HashSet<>(); /* Desta forma previne o Null pointer exceptio*/

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
     protected LocalDateTime dataCriacao = LocalDateTime.now();

     public Pessoa() {
         super();
         addPerfil(Perfil.PESSOA);

     }

    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.PESSOA);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo()) ;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + ((id == null) ? 0 : id.hashCode());
         result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        if (cpf == null){
            if(other.cpf != null)
                return false;
        } else if(!cpf.equals(other.cpf))
            return false;
        if( id == null){
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
