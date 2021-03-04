package br.com.zup.proposta.cartoes.carteiras;

import br.com.zup.proposta.cartoes.Cartao;

import java.time.LocalDateTime;

public class CarteiraResponse {

    private String id;

    private String email;

    private String associadaEm;

    private String emissor;

    private Cartao cartao;

    public Carteira toModel() {
        return new Carteira(id, email, LocalDateTime.parse(associadaEm), emissor, cartao);
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
