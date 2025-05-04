package com.matheus.HelpDesk.domain.enums;

public enum Perfil {
    ADMIN(1, "ROLE_ADMIN"),
    PESSOA(2, "PESSOA"),
    AGENTE(3, "ROLE_AGENTE"),
    FUNCIONARIO(4, "ROLE_FUNCIONARIO");


    private Integer codigo;
    private String descricao;

    Perfil(Integer codigo, String descricao) {

        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Perfil x : Perfil.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil inválido");
    }
}







