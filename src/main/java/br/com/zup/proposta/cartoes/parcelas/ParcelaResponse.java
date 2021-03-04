package br.com.zup.proposta.cartoes.parcelas;

import br.com.zup.proposta.cartoes.Cartao;

import javax.persistence.ManyToOne;

public class ParcelaResponse {

    private String id;

    private int quantidade;

    private double valor;

    private Cartao cartao;

    public Parcela toModel() {
        return new Parcela(id, quantidade, valor, cartao);
    }

    public String getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
