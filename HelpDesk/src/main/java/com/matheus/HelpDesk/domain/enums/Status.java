package com.matheus.HelpDesk.domain.enums;

public enum Status {
    Novo(0,"NOVO"),
    ABERTO(1, "ABERTO"),
    PENDENTE(2, "PENDENTE"),
    EM_ESPERA(3,"EM ESPESRA"),
    LIBERAR_ACESSO(4, "LIBERAR ACESSO"),
    COM_ESPECIALISTA(5, "COM ESPECIALISTA"),
    RESOLVIDO(6, "RESOLVIDO"),
    FECHADO(7, "FECHADO");


    private Integer codigo;
    private String descricao;

    Status(Integer codigo, String descricao) {

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
        throw new IllegalArgumentException("Status inválido");
    }
}
