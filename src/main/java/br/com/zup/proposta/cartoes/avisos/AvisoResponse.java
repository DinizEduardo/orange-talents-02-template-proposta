package br.com.zup.proposta.cartoes.avisos;

import br.com.zup.proposta.cartoes.Cartao;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class AvisoResponse {

    private String validoAte;

    private String destino;

    private Cartao cartao;

    public Aviso toModel() {
        return new Aviso(LocalDateTime.parse(validoAte), destino, cartao);
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
