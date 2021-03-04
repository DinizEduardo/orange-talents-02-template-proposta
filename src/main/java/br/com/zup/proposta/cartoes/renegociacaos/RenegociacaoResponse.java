package br.com.zup.proposta.cartoes.renegociacaos;

import java.time.LocalDateTime;

public class RenegociacaoResponse {
    private String id; // idExterno

    private Integer quantidade;

    private Double valor;

    private String dataDeCriacao;

    public Renegociacao toModel() {
        return new Renegociacao(id, quantidade, valor, LocalDateTime.parse(dataDeCriacao));
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public String getDataDeCriacao() {
        return dataDeCriacao;
    }
}
