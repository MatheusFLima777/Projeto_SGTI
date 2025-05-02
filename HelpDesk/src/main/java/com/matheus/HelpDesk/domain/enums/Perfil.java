package com.matheus.HelpDesk.domain.enums;

public enum Perfil {
    ADMIN(0, "ROLE_ADMIN"),
    PESSOA(1, "PESSOA"),
    AGENTE(2, "ROLE_AGENTE"),
    FUNCIONARIO(3, "ROLE_FUNCIONARIO");


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







