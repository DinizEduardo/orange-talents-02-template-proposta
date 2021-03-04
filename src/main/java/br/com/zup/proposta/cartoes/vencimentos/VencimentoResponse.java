package br.com.zup.proposta.cartoes.vencimentos;

import java.time.LocalDateTime;

public class VencimentoResponse {
    private String id;

    private Integer dia;

    private String dataDeCriacao;

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public String getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Vencimento toModel() {
        return new Vencimento(id, dia, LocalDateTime.parse(dataDeCriacao));
    }
}
