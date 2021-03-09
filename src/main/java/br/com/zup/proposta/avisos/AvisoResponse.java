package br.com.zup.proposta.avisos;

import br.com.zup.proposta.cartoes.Cartao;

import java.time.LocalDate;

public class AvisoResponse {

    private String validoAte;

    private String destino;

    private Cartao cartao;

    public Aviso toModel() {
        return new Aviso(LocalDate.parse(validoAte), destino, cartao);
    }

    public String getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
