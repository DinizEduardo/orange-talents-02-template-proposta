package br.com.zup.proposta.cartoes.bloqueios;

import br.com.zup.proposta.cartoes.Cartao;

import java.time.LocalDateTime;

public class BloqueioResponse {

    private String id;

    private String bloqueadoEm;

    private String sistemaResponsavel;

    private boolean ativo;

    private Cartao cartao;

    public Bloqueio toModel() {
        return new Bloqueio(id, LocalDateTime.parse(bloqueadoEm), sistemaResponsavel, ativo, cartao);
    }

}
