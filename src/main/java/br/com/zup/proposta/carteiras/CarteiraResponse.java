package br.com.zup.proposta.carteiras;

import br.com.zup.proposta.cartoes.Cartao;

import java.time.LocalDateTime;

public class CarteiraResponse {

    private String id;

    private String email;

    private String associadaEm;

    private TipoCarteiraEnum emissor;

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

    public TipoCarteiraEnum getEmissor() {
        return emissor;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Carteira toModel(Cartao cartao) {
        return new Carteira(id, email, LocalDateTime.parse(associadaEm), emissor, cartao);
    }
}
