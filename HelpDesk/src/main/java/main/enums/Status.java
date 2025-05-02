package main.enums;

public enum Status {
    ABERTO(0, "ABERTO"),
    PENDENTE(1, "PENDENTE"),
    EM_ESPERA(2,"EM ESPESRA"),
    LIBERAR_ACESSO(3, "LIBERAR ACESSO"),
    COM_ESPECIALISTA(4, "COM ESPECIALISTA"),
    RESOLVIDO(5, "RESOLVIDO"),
    FECHADO(6, "FECHADO");


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
